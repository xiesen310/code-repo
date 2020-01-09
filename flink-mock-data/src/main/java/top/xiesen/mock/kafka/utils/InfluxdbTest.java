package top.xiesen.mock.kafka.utils;


import com.zorkdata.smartdata.util.InfluxDbUtil;

public class InfluxdbTest {
    public static void main(String[] args) {
        String userName = "admin";
        String password = "admin";
        String url = "http://192.168.2.231:8086";
        String database = "selfscript_3";
        String retentionPolicy = null;
        InfluxDbUtil influxDbUtil = new InfluxDbUtil(userName,password,url,database,retentionPolicy);
        influxDbUtil.createDB("wangwei7");
    }
}
