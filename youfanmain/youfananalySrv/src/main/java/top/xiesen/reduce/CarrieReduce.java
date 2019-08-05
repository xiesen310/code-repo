package top.xiesen.reduce;

import org.apache.flink.api.common.functions.ReduceFunction;
import top.xiesen.entity.CarrieInfo;

/**
 * @Description 手机运行商 Reduce
 * @Author 谢森
 * @Date 2019/8/5 10:33
 */
public class CarrieReduce implements ReduceFunction<CarrieInfo> {
    @Override
    public CarrieInfo reduce(CarrieInfo value1, CarrieInfo value2) throws Exception {
        String carrie = value1.getCarrie();
        String groupfield = value1.getGroupfield();
        Long count1 = value1.getCount();
        Long count2 = value2.getCount();

        CarrieInfo carrieInfoFinal = new CarrieInfo();
        carrieInfoFinal.setGroupfield(groupfield);
        carrieInfoFinal.setCarrie(carrie);
        carrieInfoFinal.setCount(count1 + count2);

        return carrieInfoFinal;
    }
}
