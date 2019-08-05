package top.xiesen.map;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import top.xiesen.entity.BaiJiaInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BaiJiaMap
 * @Author 谢森
 * @Date 2019/8/5 15:04
 */
public class BaiJiaMap implements MapFunction<String, BaiJiaInfo> {
    @Override
    public BaiJiaInfo map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] orderInfos = s.split(",");

        String id  = orderInfos[0];
        String productId = orderInfos[1];
        String productTypeId = orderInfos[2];
        String createTime = orderInfos[3];
        String amount = orderInfos[4];
        String payType = orderInfos[5];
        String payTime = orderInfos[6];
        String payStatus = orderInfos[7];
        String couponAmount = orderInfos[8];
        String totalAmount = orderInfos[9];
        String refundAmount = orderInfos[10];
        String nums = orderInfos[11];
        String userId = orderInfos[12];

        BaiJiaInfo baiJiaInfo = new BaiJiaInfo();
        baiJiaInfo.setUserId(id);
        baiJiaInfo.setCreateTime(createTime);
        baiJiaInfo.setAmount(amount);
        baiJiaInfo.setPayType(payType);
        baiJiaInfo.setPayTime(payTime);
        baiJiaInfo.setPayStatus(payStatus);
        baiJiaInfo.setCouponAmount(couponAmount);
        baiJiaInfo.setTotalAmount(totalAmount);
        baiJiaInfo.setRefundAmount(refundAmount);
        String groupField = "baijia==" + userId;
        baiJiaInfo.setGroupField(groupField);

        List<BaiJiaInfo> list = new ArrayList<BaiJiaInfo>();
        list.add(baiJiaInfo);

        return baiJiaInfo;
    }
}
