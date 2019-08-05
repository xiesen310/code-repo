package top.xiesen.map;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import top.xiesen.entity.YearBase;
import top.xiesen.utils.DateUtils;
import top.xiesen.utils.HbaseUtils;

/**
 * @Description 年代标签 Map 类
 * @Author 谢森
 * @Date 2019/7/28 19:36
 */
public class YearBaseMap implements MapFunction<String, YearBase> {
    @Override
    public YearBase map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] userinfos = s.split(",");
        String userid = userinfos[0];
        String username = userinfos[1];
        String sex = userinfos[2];
        String telphone = userinfos[3];
        String email = userinfos[4];
        String age = userinfos[5];
        String registerTime = userinfos[6];
        String usertype = userinfos[7]; // 终端类型:0、pc端；1、移动端；2、小程序
        String yearBaseType = DateUtils.getYearBaseByAge(age);

        String tableName = "userflaginfo";
        String rowkey = userid;
        String familyName = "baseinfo";
        String colum = "yearbase"; // 年代
        HbaseUtils.putdata(tableName, rowkey, familyName, colum, yearBaseType);

        YearBase yearBase = new YearBase();
        String groupfield = "yearbase==" + yearBaseType;
        yearBase.setYearType(yearBaseType);
        yearBase.setCount(1L);
        yearBase.setGroupfield(groupfield);

        return yearBase;
    }
}
