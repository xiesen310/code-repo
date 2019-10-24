package top.xiesen.verify.kafka.avro;

/**
 * AvroFactory
 */
public class AvroFactory {
    /**
     * 反序列化
     */
    private static AvroDeserializer avroDeserializer = null;

    /**
     * 序列化
     */
    private static AvroSerializer avroSerializer = null;


    /**
     * 获取反序列化实例
     *
     * @param schema
     * @return
     */
    public static AvroDeserializer getDeserializerInstance(String schema) {
        if (avroDeserializer == null) {
            avroDeserializer = new AvroDeserializer(schema);
        }
        return avroDeserializer;
    }

    /**
     * 获取序列化实例
     *
     * @param schema
     * @return
     */
    public static AvroSerializer getAvorSerializerInstance(String schema) {
        if (avroSerializer == null) {
            avroSerializer = new AvroSerializer(schema);
        }
        return avroSerializer;
    }


}
