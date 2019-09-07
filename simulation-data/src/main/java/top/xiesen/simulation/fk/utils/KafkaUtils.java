package top.xiesen.simulation.fk.utils;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 加载配置文件信息
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/6/2 10:37.
 */
public class KafkaUtils {

    /**
     * 加载kafka配置信息
     *
     * @return
     */
    public static Properties load() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "zorkdata-68:9092,zorkdata-170:9092,zorkdata-biz:9092");
//        properties.put("bootstrap.servers", "master:9092,slave1:9092,slave2:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }


    /**
     * 格式化当前日期时间
     *
     * @param format 格式化时间的格式
     * @return String
     */
    public static String formatNowDate(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取当前日期进行格式化为 yyyyMMdd
     *
     * @return String
     */
    public static String getNowDateString() {
        return formatNowDate("yyyyMMdd");
    }

    /**
     * 获取当前日期进行格式化为 yyyyMMdd
     *
     * @return Integer
     */
    public static Integer getNowDateInteger() {
        return Integer.parseInt(getNowDateString());
    }

    /**
     * 获取当前日期进行格式化为 yyyyMMdd
     * @return Long
     */
    public static Long getNowDateLong() {
        return Long.parseLong(getNowDateString());
    }

    /**
     * 获取当前时间并进行格式化 HHmmssSSS
     *
     * @return String
     */
    public static String getNowTimeString() {
        return formatNowDate("HHmmssSSS");
    }

    /**
     * 获取当前时间并进行格式化 HHmmss
     *
     * @return Integer
     */
    public static Integer getNowTimeInteger() {
        return Integer.parseInt(getNowTimeString());
    }

    /**
     * 获取当前时间并进行格式化 HHmmssSSS
     * @return Long
     */
    public static Long getNowTimeLong() {
        return Long.parseLong(getNowTimeString());
    }

    /**
     * 格式化Double, 例如保留两位小数 %.2f
     *
     * @param format %.2f
     * @param f
     * @return
     */
    public static String formatDouble(String format, Double f) {
        return String.format(format, f);
    }

    /**
     * 保留两位小数
     *
     * @param f
     * @return
     */
    public static String formatDouble(Double f) {
        return formatDouble("%.2f", f);
    }
}
