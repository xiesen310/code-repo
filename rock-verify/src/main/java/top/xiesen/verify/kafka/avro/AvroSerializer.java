package top.xiesen.verify.kafka.avro;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.Utf8;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * avro 序列化
 *
 * @author 谢森
 */
public class AvroSerializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AvroSerializer.class);

    public JSONObject jsonObject;
    public JSONArray jsonArray;
    public Schema schema;
    public List<String> filedsArrayList = new ArrayList<String>();

    public AvroSerializer(String schema) {
        getKeysFromjson(schema);
    }

    /**
     * @param schema ：Avro序列化所使用的schema
     * @return void 返回类型
     * @Title: getKeysFromjson
     * @Description:用于获取Avro的keys
     */
    void getKeysFromjson(String schema) {
        this.jsonObject = JSONObject.parseObject(schema);
        this.schema = new Schema.Parser().parse(schema);
        this.jsonArray = this.jsonObject.getJSONArray("fields");
        if (filedsArrayList != null && filedsArrayList.size() > 0) {
            filedsArrayList.clear();
        }
        for (int i = 0; i < this.jsonArray.size(); i++) {
            filedsArrayList.add(this.jsonArray.getJSONObject(i).get("name").toString());
        }
    }

    /**
     * @param
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: serializing
     * @Description: 用于Avro的序列化。
     */
    private synchronized byte[] serializing(List<String> temtuple) {
        byte[] returnstr = null;
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        for (int i = 0; i < filedsArrayList.size(); i++) {
            datum.put(filedsArrayList.get(i), temtuple.get(i));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // DatumWriter 将数据对象翻译成Encoder对象可以理解的类型
        DatumWriter<GenericRecord> write = new GenericDatumWriter<GenericRecord>(this.schema);
        // 然后由Encoder写到数据流。
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);

        try {
            write.write(datum, encoder);
            encoder.flush();

        } catch (IOException e) {
            LOGGER.error("序列化失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("序列化失败", e);
                }
            }
        }
        try {
            returnstr = out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }
        return returnstr;
    }

    /**
     * 序列化json串
     *
     * @param json
     * @return
     */
    private synchronized byte[] serializing(String json) {
        byte[] returnStr = null;
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        for (int i = 0; i < filedsArrayList.size(); i++) {
            datum.put(filedsArrayList.get(i), new Utf8(String.valueOf(jsonObject.get(filedsArrayList.get(i)))));
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // DatumWriter 将数据对象翻译成Encoder对象可以理解的类型
        DatumWriter<GenericRecord> write = new GenericDatumWriter<GenericRecord>(this.schema);
        // 然后由Encoder写到数据流。
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);

        try {
            write.write(datum, encoder);
            encoder.flush();
        } catch (IOException e) {
            LOGGER.error("序列化失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("序列化失败", e);
                }
            }
        }
        try {
            returnStr = out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }
        return returnStr;
    }

    /**
     * 序列化json对象
     *
     * @param jsonObject
     * @return
     */
    private synchronized byte[] serializing(JSONObject jsonObject) {
        byte[] returnStr = null;
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        for (int i = 0; i < filedsArrayList.size(); i++) {
            datum.put(filedsArrayList.get(i), jsonObject.get(filedsArrayList.get(i)));
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // DatumWriter 将数据对象翻译成Encoder对象可以理解的类型
        DatumWriter<GenericRecord> write = new GenericDatumWriter<GenericRecord>(this.schema);
        // 然后由Encoder写到数据流。
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);

        try {
            write.write(datum, encoder);
            encoder.flush();
        } catch (IOException e) {
            LOGGER.error("序列化失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("序列化失败", e);
                }
            }
        }
        try {
            returnStr = out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }
        return returnStr;
    }

    /**
     * 序列化对象
     */
    public synchronized byte[] serializing(GenericRecord datum) {
        byte[] returnStr = null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // DatumWriter 将数据对象翻译成Encoder对象可以理解的类型
        DatumWriter<GenericRecord> write = new GenericDatumWriter<GenericRecord>(this.schema);
        // 然后由Encoder写到数据流。
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);

        try {
            write.write(datum, encoder);
            encoder.flush();
        } catch (IOException e) {
            LOGGER.error("序列化失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("序列化失败", e);
                }
            }
        }
        try {
            returnStr = out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }

        // GenericRecord s = AvroDeserializerFactory.getTopicmetadataDeserializer().deserializing(returnstr);
        return returnStr;
    }

    /**
     * 序列化对象
     */
    public synchronized byte[] serializingLog(String logTypeName, String timestamp, String source, String offset, Map<String, String> dimensions, Map<String, Double> metrics,
                                              Map<String, String> normalFields) {
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        datum.put(0, logTypeName);
        datum.put(1, timestamp);
        datum.put(2, source);
        datum.put(3, offset);
        datum.put(4, dimensions);
        datum.put(5, metrics);
        datum.put(6, normalFields);

        return serializing(datum);
    }

    /**
     * 序列化对象
     */
    public synchronized byte[] serializingMetric(String metricSetName, String timestamp, Map<String, String> dimensions, Map<String, Double> metrics) {
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        datum.put(0, metricSetName);
        datum.put(1, timestamp);
        datum.put(2, dimensions);
        datum.put(3, metrics);

        return serializing(datum);
    }

    private synchronized byte[] serializing(GenericRecord genericRecord, String key[]) {
        byte[] returnStr = null;
        GenericRecord datum = new GenericData.Record(this.schema);
        // 将数据加到datum中
        for (int i = 0; i < filedsArrayList.size(); i++) {
            datum.put(filedsArrayList.get(i), new Utf8(String.valueOf(genericRecord.get(key[i]))));
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // DatumWriter 将数据对象翻译成Encoder对象可以理解的类型
        DatumWriter<GenericRecord> write = new GenericDatumWriter<GenericRecord>(this.schema);
        // 然后由Encoder写到数据流。
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);

        try {
            write.write(datum, encoder);
            encoder.flush();

        } catch (IOException e) {
            LOGGER.error("序列化失败", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    LOGGER.error("序列化失败", e);
                }
            }
        }
        try {
            returnStr = out.toByteArray();
        } catch (Exception e) {
            LOGGER.error("序列化失败", e);
        }
        return returnStr;
    }
}
