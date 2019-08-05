package top.xiesen.task;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;
import top.xiesen.entity.EmailInfo;
import top.xiesen.map.EmailMap;
import top.xiesen.reduce.EmailReduce;
import top.xiesen.utils.MongoUtils;

import java.util.List;

/**
 * @Description 用户画像之邮箱运行商标签计算
 * @Author 谢森
 * @Date 2019/8/5 11:16
 */
public class EmailTask {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);
        DataSet<String> text = env.readTextFile(params.get("input"));

        DataSet<EmailInfo> mapResult = text.map(new EmailMap());
        DataSet<EmailInfo> reduceResult = mapResult.groupBy("groupField").reduce(new EmailReduce());

        try {
            List<EmailInfo> emailInfoList = reduceResult.collect();
            for (EmailInfo emailInfo : emailInfoList) {
                System.out.println("EmailInfo: " + emailInfo);
                String emailType = emailInfo.getEmailType();
                Long count = emailInfo.getCount();
                Document document = MongoUtils.findoneby("emailstatics", "youfanPortrait", emailType);
                if (document == null) {
                    document = new Document();
                    document.put("emailType", emailType);
                    document.put("count", count);
                } else {
                    Long countPre = document.getLong("count");
                    Long total = countPre + count;
                    document.put("count", total);
                }
                MongoUtils.saveorupdatemongo("emailstatics", "youfanPortrait", document);
                env.execute("EmailTask");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
