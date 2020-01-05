package top.xiesen.security.wiremock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    private static String filePath = "D:\\develop\\workspace\\code-repo\\xiesen-security\\xiesen-security-demo\\src\\main\\resources\\mock\\response\\alarm_rules.txt";

    public static void main(String[] args) {

        int num = 3000;
        generateData(num);
    }

    public static void generateData (int num) {

        RespDTO respDTO = new RespDTO();
        respDTO.setCode(200 + "");
        respDTO.setMessage("success");
        respDTO.setData(data(num));
        String s = JSON.toJSONString(respDTO);
        saveAsFileWriter(s);
    }
    private static JSONArray data(int num) {
        List<AlarmRule> list = new ArrayList<>();
        for (int i = 0; i < 3 * num; i += 3) {
            AlarmRule alarmRule = new AlarmRule();
            alarmRule.setId(i + "");
            alarmRule.setAlarmContent("testContent");
            alarmRule.setAlarmTitle("testTitle");
            alarmRule.setRuleType(2);
            alarmRule.setCalenderId("52");
            alarmRule.setDataSubsetName("cpu_cpu_used_pct");
            alarmRule.setIsAggregate("0");
            alarmRule.setInformType("1,2,2");
            alarmRule.setRuleExpression("avg(last(CPU2,4))!=1");
            String parentId = String.valueOf(i + 1);
            alarmRule.setParentId(parentId);


            AlarmRule alarmRule2 = new AlarmRule();
            alarmRule2.setId(parentId);
            alarmRule2.setAlarmContent("testContent");
            alarmRule2.setAlarmTitle("testTitle");
            alarmRule2.setCalenderId("52");
            alarmRule2.setRuleType(2);
            alarmRule2.setDataSubsetName("cpu_cpu_used_pct");
            alarmRule2.setIsAggregate("0");
            alarmRule2.setInformType("1,2,2");
            alarmRule2.setRuleExpression("avg(last(CPU2,4))!=1");

            String parentId_1 = String.valueOf(i + 2);

            alarmRule2.setParentId(parentId_1);

            AlarmRule alarmRule3 = new AlarmRule();
            alarmRule3.setId(parentId_1);
            alarmRule3.setAlarmContent("testContent");
            alarmRule3.setAlarmTitle("testTitle");
            alarmRule3.setCalenderId("52");
            alarmRule3.setRuleType(2);
            alarmRule3.setDataSubsetName("cpu_cpu_used_pct");
            alarmRule3.setIsAggregate("0");
            alarmRule3.setInformType("1,2,2");
            alarmRule3.setRuleExpression("avg(last(CPU2,4))!=1");
            alarmRule3.setParentId(String.valueOf(-1));
            list.add(alarmRule);
            list.add(alarmRule2);
            list.add(alarmRule3);
        }
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        return jsonArray;
    }

    private static void saveAsFileWriter(String content) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath, true);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
