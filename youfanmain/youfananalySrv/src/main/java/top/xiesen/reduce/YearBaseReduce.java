package top.xiesen.reduce;

import org.apache.flink.api.common.functions.ReduceFunction;
import top.xiesen.entity.YearBase;

/**
 * @Description 年代标签 reduce
 * @Author 谢森
 * @Date 2019/7/29 15:58
 */
public class YearBaseReduce implements ReduceFunction<YearBase> {

    @Override
    public YearBase reduce(YearBase value1, YearBase value2) throws Exception {
        String yearType = value1.getYearType();
        String groupfield = value1.getGroupfield();
        Long count1 = value1.getCount();
        Long count2 = value2.getCount();

        YearBase finalYearBase = new YearBase();
        finalYearBase.setYearType(yearType);
        finalYearBase.setCount(count1 + count2);
        finalYearBase.setGroupfield(groupfield);

        return finalYearBase;
    }
}
