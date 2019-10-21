package top.xiesen.simulation.zork;

import com.alibaba.fastjson.JSONObject;
import org.apache.avro.generic.GenericRecord;
import top.xiesen.simulation.zork.avro.AvroDeserializer;
import top.xiesen.simulation.zork.avro.AvroDeserializerFactory;
import top.xiesen.simulation.zork.avro.AvroSerializer;
import top.xiesen.simulation.zork.avro.AvroSerializerFactory;

import java.util.HashMap;
import java.util.Map;

public class AvroTest {
    public static void main(String[] args) {

        String logTypeName = "aaa";
        String timestamp = "";
        String source = "sss";
        String offset = "aa";
        Map<String, String> dimensions = new HashMap<>();
        Map<String, Double> metrics = new HashMap<>();
        Map<String, String> normalFields = new HashMap<>();
        Map<String,Object> event = new HashMap<>();
        event.put("logTypeName",logTypeName);
        event.put("timestamp",timestamp);
        event.put("source",source);
        event.put("offset",offset);
        event.put("offset",offset);
        event.put("dimensions",dimensions);
        event.put("measures",metrics);
        event.put("normalFields",normalFields);
        JSONObject jsonObject = new JSONObject(event);
        System.out.println(jsonObject);

        AvroSerializer avorSerializer = AvroSerializerFactory.getLogAvorSerializer();
        byte[] bytes = avorSerializer.serializingLog(logTypeName, timestamp, source, offset, dimensions, metrics, normalFields);


        AvroDeserializer logsDeserializer = AvroDeserializerFactory.getLogsDeserializer();
        GenericRecord deserializing = logsDeserializer.deserializing(bytes);
        System.out.println(deserializing.toString());

    }
}
