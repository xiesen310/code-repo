package top.xiesen.schema;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * 反序列化
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/3/7 13:50.
 */
@SuppressWarnings("all")
public class AvorDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return null;
    }

    @Override
    public void close() {

    }
}
