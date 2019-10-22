package top.xiesen.verify.utils;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * 如果为空，返回true，否则返回false
     *
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str != null && str.length() != 0) ? false : true;
    }

    public static List<String> numbers = new ArrayList<String>() {
        {
            add("0");
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
        }
    };

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);

        return isNum.matches();
    }

    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();

        return str.equals("") || str.equalsIgnoreCase("NULL");
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        str = str.trim();
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Double getDouble(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        try {
            return Double.valueOf(str);
        } catch (Exception Ex) {
            return null;
        }
    }

    public static double getDouble(String str, double defaultValue) {
        Double d = getDouble(str);
        return d == null ? defaultValue : d;
    }

    public static long getLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        str = str.trim();
        try {
            return Long.valueOf(str);
        } catch (Exception Ex) {
            return defaultValue;
        }
    }

    public static int getInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        str = str.trim();
        try {
            return Integer.valueOf(str);
        } catch (Exception Ex) {
            return defaultValue;
        }
    }

}
