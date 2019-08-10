package top.xiesen.sink;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.bson.Document;
import top.xiesen.entity.BrandLike;
import top.xiesen.utils.MongoUtils;

/**
 * @Description BrandLinkeSink
 * @Author 谢森
 * @Date 2019/8/10 15:42
 */
public class BrandLinkeSink implements SinkFunction<BrandLike> {
    @Override
    public void invoke(BrandLike value, Context context) throws Exception {
        String brand = value.getBrand();
        Long count = value.getCount();
        Document document = MongoUtils.findoneby("brandlikestatics", "youfanPortrait", brand);
        if (document == null) {
            document = new Document();
            document.put("info", brand);
            document.put("count", count);
        } else {
            Long countpre = document.getLong("count");
            Long total = countpre + count;
            document.put("count", total);
        }
        MongoUtils.saveorupdatemongo("brandlikestatics", "youfanPortrait", document);

    }
}
