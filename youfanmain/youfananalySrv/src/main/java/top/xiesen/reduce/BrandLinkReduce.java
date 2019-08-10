package top.xiesen.reduce;

import org.apache.flink.api.common.functions.ReduceFunction;
import top.xiesen.entity.BrandLike;

/**
 * @Description BrandLinkReduce
 * @Author 谢森
 * @Date 2019/8/10 15:26
 */
public class BrandLinkReduce implements ReduceFunction<BrandLike> {
    @Override
    public BrandLike reduce(BrandLike value1, BrandLike value2) throws Exception {
        String brand = value1.getBrand();
        Long count1 = value1.getCount();
        Long count2 = value2.getCount();

        BrandLike finalBrandLinke = new BrandLike();
        finalBrandLinke.setBrand(brand);
        finalBrandLinke.setCount(count1 + count2);
        return finalBrandLinke;
    }
}
