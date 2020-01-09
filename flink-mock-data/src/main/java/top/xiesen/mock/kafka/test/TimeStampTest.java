package top.xiesen.mock.kafka.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampTest {
    public static Date StrToDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            System.out.print("日期转换异常");
        }
        return date;
    }

    private static DateTimeFormatter dateFormat1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        String mestime = String.valueOf("20190424092143333");
        String datetime = mestime.substring(0, 15);
        Date date = StrToDate(datetime);
        String times = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date).toString();
        DateTime dateTime2 = DateTime.parse(times, dateFormat1);
        String timestamp = dateTime2.toString();
        System.out.println(timestamp);
    }
}
