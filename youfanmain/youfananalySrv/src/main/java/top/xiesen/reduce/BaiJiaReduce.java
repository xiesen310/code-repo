package top.xiesen.reduce;

import org.apache.flink.api.common.functions.ReduceFunction;
import top.xiesen.entity.BaiJiaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BaiJiaReduce
 * @Author 谢森
 * @Date 2019/8/5 16:20
 */
public class BaiJiaReduce implements ReduceFunction<BaiJiaInfo> {
    @Override
    public BaiJiaInfo reduce(BaiJiaInfo b1, BaiJiaInfo b2) throws Exception {
        String userId = b1.getUserId();
        List<BaiJiaInfo> list1 = b1.getList();
        List<BaiJiaInfo> list2 = b2.getList();

        List<BaiJiaInfo> finalList = new ArrayList<>();
        finalList.addAll(list1);
        finalList.addAll(list2);

        BaiJiaInfo baiJiaInfoFinal = new BaiJiaInfo();
        baiJiaInfoFinal.setUserId(userId);
        baiJiaInfoFinal.setList(finalList);
        return baiJiaInfoFinal;
    }
}
