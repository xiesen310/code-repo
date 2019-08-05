package top.xiesen.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 时间工具类
 * @Author 谢森
 * @Date 2019/7/28 20:15
 */
public class DateUtils {
    public static String getYearBaseByAge(String age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -Integer.valueOf(age));
        Date newDate = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String newDateString = dateFormat.format(newDate);
        Integer newDateInteger = Integer.valueOf(newDateString);

        String yearBaseType = "未知";
        if (newDateInteger >= 1940 && newDateInteger < 1950) {
            yearBaseType = "40后";
        } else if (newDateInteger >= 1950 && newDateInteger < 1960) {
            yearBaseType = "50后";
        } else if (newDateInteger >= 1960 && newDateInteger < 1970) {
            yearBaseType = "60后";
        } else if (newDateInteger >= 1970 && newDateInteger < 1980) {
            yearBaseType = "70后";
        } else if (newDateInteger >= 1980 && newDateInteger < 1990) {
            yearBaseType = "80后";
        } else if (newDateInteger >= 1990 && newDateInteger < 2000) {
            yearBaseType = "90后";
        } else if (newDateInteger >= 2000 && newDateInteger < 2010) {
            yearBaseType = "00后";
        } else if (newDateInteger >= 2010) {
            yearBaseType = "10后";
        }
        return yearBaseType;
    }

    /**
     * 计算购买的频率
     * @param startTime
     * @param endTime
     * @param dateFormatString
     * @return
     * @throws ParseException
     */
    public static int getBetweenByStartAndEnd(String startTime,String endTime,String dateFormatString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        Date start = dateFormat.parse(startTime);
        Date end = dateFormat.parse(endTime);

        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        endCalendar.setTime(end);

        int days = 0;

        while (startCalendar.before(endCalendar)) {
            startCalendar.add(Calendar.DAY_OF_YEAR,1);
            days += 1;
        }
        return days;
    }
}
