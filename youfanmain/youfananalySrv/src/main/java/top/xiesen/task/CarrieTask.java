package top.xiesen.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.operators.ReduceOperator;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;
import top.xiesen.entity.CarrieInfo;
import top.xiesen.map.CarrieMap;
import top.xiesen.reduce.CarrieReduce;
import top.xiesen.utils.MongoUtils;

import java.util.List;

/**
 * @Description CarrieTask 手机运行商标签计算
 * @Author 谢森
 * @Date 2019/7/30 22:21
 */
public class CarrieTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);

        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<CarrieInfo> mapResult = text.map(new CarrieMap());
        DataSet<CarrieInfo> reduceResult = mapResult.groupBy("groupfield").reduce(new CarrieReduce());

        try {
            List<CarrieInfo> carrieInfoList = reduceResult.collect();
            for (CarrieInfo carrieInfo : carrieInfoList) {
                String carrie = carrieInfo.getCarrie();
                Long count = carrieInfo.getCount();

                Document document = MongoUtils.findoneby("carriestatics", "youfanPortrait", carrie);
                if (document == null) {
                    document = new Document();
                    document.put("carrie", carrie);
                    document.put("count", count);
                } else {
                    Long countpre = document.getLong("count");
                    Long total = countpre + count;
                    document.put("count", total);
                }
                MongoUtils.saveorupdatemongo("carriestatics", "youfanPortrait", document);
            }
            env.execute("CarrieTask");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
