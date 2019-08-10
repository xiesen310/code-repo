package top.xiesen.map;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import top.xiesen.entity.BrandLike;
import top.xiesen.kafka.KafkaEvent;
import top.xiesen.log.ScanProductLog;
import top.xiesen.utils.HbaseUtils;
import top.xiesen.utils.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description BrandLinkMap
 * @Author 谢森
 * @Date 2019/8/10 14:49
 */
public class BrandLinkMap implements FlatMapFunction<KafkaEvent, BrandLike> {
    @Override
    public void flatMap(KafkaEvent kafkaEvent, Collector<BrandLike> out) throws Exception {
        String data = kafkaEvent.getWord();
        ScanProductLog scanProductLog = JSONObject.parseObject(data, ScanProductLog.class);
        int userId = scanProductLog.getUserId();
        String brand = scanProductLog.getBrand();
        String tableName = "userflaginfo";
        String rowkey = userId + "";
        String familyName = "userbehavior";
        String colum = "brandlist"; // 运行商
        String mapData = HbaseUtils.getdata(tableName, rowkey, familyName, colum);

        Map<String, Long> map = new HashMap<String, Long>();
        if (StringUtils.isBlank(mapData)) {
            map = JSONObject.parseObject(mapData, Map.class);
        }
        // 获取之前的品牌偏好
        String maxPreBrand = MapUtils.getMaxbyMap(map);

        long prebrand = map.get(brand) == null ? 01 : map.get(brand);
        map.put(brand, prebrand + 1);
        String finalString = JSONObject.toJSONString(map);
        HbaseUtils.putdata(tableName, rowkey, familyName, colum, finalString);

        String maxBrand = MapUtils.getMaxbyMap(map);
        if (StringUtils.isNotBlank(maxBrand) && maxPreBrand.equals(maxBrand)) {
            BrandLike brandLike = new BrandLike();
            brandLike.setBrand(maxPreBrand);
            brandLike.setGroupFieds("==brandlike==" + maxPreBrand);
            brandLike.setCount(-1L);
            out.collect(brandLike);
        }

        BrandLike brandLike = new BrandLike();
        brandLike.setBrand(maxBrand);
        brandLike.setCount(1L);
        brandLike.setGroupFieds("==brandlike==" + maxBrand);
        out.collect(brandLike);

        colum = "brandlink";
        HbaseUtils.putdata(tableName, rowkey, familyName, colum, maxBrand);

    }
}
