package top.xiesen.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间转换工具类
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/3/3 11:34.
 */
public class TimeConvertUtils {

    public static void UTCToCST(String UTCStr, String format) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date = sdf.parse(UTCStr);
        System.out.println("UTC时间: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        System.out.println("北京时间: " + calendar.getTime());
    }


    /**
     * 获取utc时间
     * 2019-03-03T07:41:51.858Z
     * @return
     */
    public static String getUTC() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
    }

    /**
     * 获取日期 例如: 20190703
     * @return
     */
    public static String getDate(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }


    /**
     * 2019-03-03T07:41:51.858Z
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        UTCToCST("2017-11-27T03:16:03.944Z", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        System.out.println("CST 时间: "+ new Date());

        System.setProperty("user.timezone", "UTC");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC 时间: "+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));

        System.err.println(getUTC());

        System.out.println(getDate());


    }

}
