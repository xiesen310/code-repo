package top.xiesen.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description MapUtils
 * @Author 谢森
 * @Date 2019/8/10 15:08
 */
public class MapUtils {

    /**
     * 获取最受欢迎的牌子
     *
     * @param dataMap dataMap
     * @return
     */
    public static String getMaxbyMap(Map<String, Long> dataMap) {
        if (dataMap.isEmpty()) {
            return null;
        }

        TreeMap<Long, String> map = new TreeMap<Long, String>(new Comparator<Long>() {
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });
        Set<Map.Entry<String, Long>> set = dataMap.entrySet();
        for (Map.Entry<String, Long> entry : set) {
            String key = entry.getKey();
            Long value = entry.getValue();
            map.put(value, key);
        }
        return map.get(map.firstKey());
    }
}
