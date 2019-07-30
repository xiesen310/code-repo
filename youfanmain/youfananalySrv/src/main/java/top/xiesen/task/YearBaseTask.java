package top.xiesen.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;
import top.xiesen.entity.YearBase;
import top.xiesen.map.YearBaseMap;
import top.xiesen.reduce.YearBaseReduce;
import top.xiesen.utils.MongoUtils;

import java.util.List;

/**
 * @Description 年代标签
 * 年代: 40年代、50年代、60年代、70年代、80年代、90年代、00年代、10年代
 * 统计每个年代群里的数量，做到近实时统计，没半个小时进行一次任务统计
 * @Author 谢森
 * @Date 2019/7/28 19:25
 */
public class YearBaseTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<YearBase> mapresult = text.map(new YearBaseMap());
        DataSet<YearBase> reduceResult = mapresult.groupBy("groupfield").reduce(new YearBaseReduce());

        try {
            mapresult.print();
            List<YearBase> yearBaseList = reduceResult.collect();
            for (YearBase yearBase : yearBaseList) {
                System.out.println("yearBase: " + yearBase);
                String yearType = yearBase.getYearType();
                Long count = yearBase.getCount();
                Document document = MongoUtils.findoneby("yearbasestatics", "youfanPortrait", yearType);
                if (document == null) {
                    document = new Document();
                    document.put("yearbasetype", yearType);
                    document.put("count", count);
                } else {
                    Long countpre = document.getLong("count");
                    Long total = countpre + count;
                    document.put("count", total);
                }
                MongoUtils.saveorupdatemongo("yearbasestatics", "youfanPortrait", document);
                env.execute("year base");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
