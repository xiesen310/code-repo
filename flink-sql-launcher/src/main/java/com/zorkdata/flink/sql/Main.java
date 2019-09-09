package com.zorkdata.flink.sql;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.io.Charsets;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.client.program.ContextEnvironment;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String CLASS_FILE_NAME_FMT = "class_path_%d";

    private static final ObjectMapper objMapper = new ObjectMapper();

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final int failureRate = 3;

    private static final int failureInterval = 6; //min

    private static final int delayInterval = 10; //sec

    private static org.apache.calcite.sql.parser.SqlParser.Config config = org.apache.calcite.sql.parser.SqlParser
            .configBuilder()
            .setLex(Lex.MYSQL)
            .build();

    public static void main(String[] args) throws Exception {

        Options options = new Options();
        options.addOption("sql", true, "sql config");
        options.addOption("name", true, "job name");
        options.addOption("addjar", true, "add jar");
        options.addOption("localSqlPluginPath", true, "local sql plugin path");
        options.addOption("remoteSqlPluginPath", true, "remote sql plugin path");
        options.addOption("confProp", true, "env properties");
        options.addOption("mode", true, "deploy mode");

        options.addOption("savePointPath", true, "Savepoint restore path");
        options.addOption("allowNonRestoredState", true, "Flag indicating whether non restored state is allowed if the savepoint");

        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        String sql = cl.getOptionValue("sql");
        String name = cl.getOptionValue("name");
        String addJarListStr = cl.getOptionValue("addjar");
        String localSqlPluginPath = cl.getOptionValue("localSqlPluginPath");
        String remoteSqlPluginPath = cl.getOptionValue("remoteSqlPluginPath");
        String deployMode = cl.getOptionValue("mode");
        String confProp = cl.getOptionValue("confProp");

        Preconditions.checkNotNull(sql, "parameters of sql is required");
        Preconditions.checkNotNull(name, "parameters of name is required");
        Preconditions.checkNotNull(localSqlPluginPath, "parameters of localSqlPluginPath is required");

        sql = URLDecoder.decode(sql, Charsets.UTF_8.name());
        SqlParser.setLocalSqlPluginRoot(localSqlPluginPath);

        List<String> addJarFileList = Lists.newArrayList();
        if(!Strings.isNullOrEmpty(addJarListStr)){
            addJarListStr = URLDecoder.decode(addJarListStr, Charsets.UTF_8.name());
            addJarFileList = objMapper.readValue(addJarListStr, List.class);
        }

        ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
        DtClassLoader parentClassloader = new DtClassLoader(new URL[]{}, threadClassLoader);
        Thread.currentThread().setContextClassLoader(parentClassloader);

        confProp = URLDecoder.decode(confProp, Charsets.UTF_8.toString());
        Properties confProperties = PluginUtil.jsonStrToObject(confProp, Properties.class);
        StreamExecutionEnvironment env = getStreamExeEnv(confProperties, deployMode);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.getTableEnvironment(env);

        List<URL> jarURList = Lists.newArrayList();
        SqlTree sqlTree = SqlParser.parseSql(sql);

        //Get External jar to load
        for(String addJarPath : addJarFileList){
            File tmpFile = new File(addJarPath);
            jarURList.add(tmpFile.toURI().toURL());
        }

        Map<String, SideTableInfo> sideTableMap = Maps.newHashMap();
        Map<String, Table> registerTableCache = Maps.newHashMap();

        //register udf
        registerUDF(sqlTree, jarURList, parentClassloader, tableEnv);
        //register table schema
        registerTable(sqlTree, env, tableEnv, localSqlPluginPath, remoteSqlPluginPath, sideTableMap, registerTableCache);

        SideSqlExec sideSqlExec = new SideSqlExec();
        sideSqlExec.setLocalSqlPluginPath(localSqlPluginPath);

        for (CreateTmpTableParser.SqlParserResult result : sqlTree.getTmpSqlList()) {
            sideSqlExec.registerTmpTable(result, sideTableMap, tableEnv, registerTableCache);
        }

        for (InsertSqlParser.SqlParseResult result : sqlTree.getExecSqlList()) {
            if(LOG.isInfoEnabled()){
                LOG.info("exe-sql:\n" + result.getExecSql());
            }

            boolean isSide = false;

            for (String tableName : result.getTargetTableList()) {
                if (sqlTree.getTmpTableMap().containsKey(tableName)) {
                    CreateTmpTableParser.SqlParserResult tmp = sqlTree.getTmpTableMap().get(tableName);
                    String realSql = DtStringUtil.replaceIgnoreQuota(result.getExecSql(), "`", "");
                    SqlNode sqlNode = org.apache.calcite.sql.parser.SqlParser.create(realSql, config).parseStmt();
                    String tmpSql = ((SqlInsert) sqlNode).getSource().toString();
                    tmp.setExecSql(tmpSql);
                    sideSqlExec.registerTmpTable(tmp, sideTableMap, tableEnv, registerTableCache);
                } else {
                    for(String sourceTable : result.getSourceTableList()){
                        if(sideTableMap.containsKey(sourceTable)){
                            isSide = true;
                            break;
                        }
                    }

                    if(isSide){
                        //sql-dimensional table contains the dimension table of execution
                        sideSqlExec.exec(result.getExecSql(), sideTableMap, tableEnv, registerTableCache);
                    }else{
                        FlinkSQLExec.sqlUpdate(tableEnv, result.getExecSql());
                        if(LOG.isInfoEnabled()){
                            LOG.info("exec sql: " + result.getExecSql());
                        }
                    }
                }
            }
        }

        if(env instanceof MyLocalStreamEnvironment) {
            List<URL> urlList = new ArrayList<>();
            urlList.addAll(Arrays.asList(parentClassloader.getURLs()));
            ((MyLocalStreamEnvironment) env).setClasspaths(urlList);
        }

        env.execute(name);
    }

    /**
     * This part is just to add classpath for the jar when reading remote execution, and will not submit jar from a local
     * @param env
     * @param classPathSet
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void addEnvClassPath(StreamExecutionEnvironment env, Set<URL> classPathSet) throws NoSuchFieldException, IllegalAccessException {
        if(env instanceof StreamContextEnvironment){
            Field field = env.getClass().getDeclaredField("ctx");
            field.setAccessible(true);
            ContextEnvironment contextEnvironment= (ContextEnvironment) field.get(env);
            for(URL url : classPathSet){
                contextEnvironment.getClasspaths().add(url);
            }
        }
    }

    private static void registerUDF(SqlTree sqlTree, List<URL> jarURList, URLClassLoader parentClassloader,
                                    StreamTableEnvironment tableEnv)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //register urf
        URLClassLoader classLoader = null;
        List<CreateFuncParser.SqlParserResult> funcList = sqlTree.getFunctionList();
        for (CreateFuncParser.SqlParserResult funcInfo : funcList) {
            //classloader
            if (classLoader == null) {
                classLoader = FlinkUtil.loadExtraJar(jarURList, parentClassloader);
            }
            FlinkUtil.registerUDF(funcInfo.getType(), funcInfo.getClassName(), funcInfo.getName(),
                    tableEnv, classLoader);
        }
    }


    private static void registerTable(SqlTree sqlTree, StreamExecutionEnvironment env, StreamTableEnvironment tableEnv,
                                      String localSqlPluginPath, String remoteSqlPluginPath,
                                      Map<String, SideTableInfo> sideTableMap, Map<String, Table> registerTableCache) throws Exception {
        Set<URL> classPathSet = Sets.newHashSet();
        WaterMarkerAssigner waterMarkerAssigner = new WaterMarkerAssigner();
        for (TableInfo tableInfo : sqlTree.getTableInfoMap().values()) {

            if (tableInfo instanceof SourceTableInfo) {

                SourceTableInfo sourceTableInfo = (SourceTableInfo) tableInfo;
                Table table = StreamSourceFactory.getStreamSource(sourceTableInfo, env, tableEnv, localSqlPluginPath);
                tableEnv.registerTable(sourceTableInfo.getAdaptName(), table);
                //Note --- parameter conversion function can not be used inside a function of the type of polymerization
                //Create table in which the function is arranged only need adaptation sql
                String adaptSql = sourceTableInfo.getAdaptSelectSql();
                Table adaptTable = adaptSql == null ? table : tableEnv.sqlQuery(adaptSql);

                RowTypeInfo typeInfo = new RowTypeInfo(adaptTable.getSchema().getTypes(), adaptTable.getSchema().getColumnNames());
                DataStream adaptStream = tableEnv.toRetractStream(adaptTable, typeInfo)
                        .map((Tuple2<Boolean, Row> f0) -> { return f0.f1; })
                        .returns(typeInfo);

                String fields = String.join(",", typeInfo.getFieldNames());

                if(waterMarkerAssigner.checkNeedAssignWaterMarker(sourceTableInfo)){
                    adaptStream = waterMarkerAssigner.assignWaterMarker(adaptStream, typeInfo, sourceTableInfo);
                    fields += ",ROWTIME.ROWTIME";
                }else{
                    fields += ",PROCTIME.PROCTIME";
                }

                Table regTable = tableEnv.fromDataStream(adaptStream, fields);
                tableEnv.registerTable(tableInfo.getName(), regTable);
                if(LOG.isInfoEnabled()){
                    LOG.info("registe table {} success.", tableInfo.getName());
                }
                registerTableCache.put(tableInfo.getName(), regTable);
                classPathSet.add(PluginUtil.getRemoteJarFilePath(tableInfo.getType(), SourceTableInfo.SOURCE_SUFFIX, remoteSqlPluginPath, localSqlPluginPath));
            } else if (tableInfo instanceof TargetTableInfo) {

                TableSink tableSink = StreamSinkFactory.getTableSink((TargetTableInfo) tableInfo, localSqlPluginPath);
                TypeInformation[] flinkTypes = FlinkUtil.transformTypes(tableInfo.getFieldClasses());
                tableEnv.registerTableSink(tableInfo.getName(), tableInfo.getFields(), flinkTypes, tableSink);
                classPathSet.add( PluginUtil.getRemoteJarFilePath(tableInfo.getType(), TargetTableInfo.TARGET_SUFFIX, remoteSqlPluginPath, localSqlPluginPath));
            } else if(tableInfo instanceof SideTableInfo){

                String sideOperator = ECacheType.ALL.name().equals(((SideTableInfo) tableInfo).getCacheType()) ? "all" : "async";
                sideTableMap.put(tableInfo.getName(), (SideTableInfo) tableInfo);
                classPathSet.add(PluginUtil.getRemoteSideJarFilePath(tableInfo.getType(), sideOperator, SideTableInfo.TARGET_SUFFIX, remoteSqlPluginPath, localSqlPluginPath));
            }else {
                throw new RuntimeException("not support table type:" + tableInfo.getType());
            }
        }

        //The plug-in information corresponding to the table is loaded into the classPath env
        addEnvClassPath(env, classPathSet);
        int i = 0;
        for(URL url : classPathSet){
            String classFileName = String.format(CLASS_FILE_NAME_FMT, i);
            env.registerCachedFile(url.getPath(),  classFileName, true);
            i++;
        }
    }

    private static StreamExecutionEnvironment getStreamExeEnv(Properties confProperties, String deployMode) throws IOException, NoSuchMethodException {
        StreamExecutionEnvironment env = !ClusterMode.local.name().equals(deployMode) ?
                StreamExecutionEnvironment.getExecutionEnvironment() :
                new MyLocalStreamEnvironment();

        env.setParallelism(FlinkUtil.getEnvParallelism(confProperties));
        Configuration globalJobParameters = new Configuration();
        Method method = Configuration.class.getDeclaredMethod("setValueInternal", String.class, Object.class);
        method.setAccessible(true);

        confProperties.forEach((key,val) -> {
            try {
                method.invoke(globalJobParameters, key, val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        ExecutionConfig exeConfig = env.getConfig();
        if(exeConfig.getGlobalJobParameters() == null){
            exeConfig.setGlobalJobParameters(globalJobParameters);
        }else if(exeConfig.getGlobalJobParameters() instanceof Configuration){
            ((Configuration) exeConfig.getGlobalJobParameters()).addAll(globalJobParameters);
        }


        if(FlinkUtil.getMaxEnvParallelism(confProperties) > 0){
            env.setMaxParallelism(FlinkUtil.getMaxEnvParallelism(confProperties));
        }

        if(FlinkUtil.getBufferTimeoutMillis(confProperties) > 0){
            env.setBufferTimeout(FlinkUtil.getBufferTimeoutMillis(confProperties));
        }

        env.setRestartStrategy(RestartStrategies.failureRateRestart(
                failureRate,
                Time.of(failureInterval, TimeUnit.MINUTES),
                Time.of(delayInterval, TimeUnit.SECONDS)
        ));

        FlinkUtil.setStreamTimeCharacteristic(env, confProperties);
        FlinkUtil.openCheckpoint(env, confProperties);

        return env;
    }
}
