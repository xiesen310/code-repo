package top.xiesen;

import org.apache.avro.generic.GenericRecord;
import top.xiesen.schema.LogAvroBean;
import top.xiesen.simulation.zork.avro.AvroDeserializer;
import top.xiesen.simulation.zork.avro.AvroDeserializerFactory;
import top.xiesen.simulation.zork.avro.AvroSerializer;
import top.xiesen.simulation.zork.avro.LogAvroMacroDef;
import top.xiesen.utils.AvroUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException {
        String logTypeName = "log";
        String timestamp = "2019-04-03 10:35:00";
        String source = "/var/log/elasticseach/es.log";
        String offset = "122";
        Map<String,String> dimensions = new HashMap<String, String>();
        dimensions.put("hostname","localhost");
        dimensions.put("appsystem","tdx");
        dimensions.put("appprogramname","tdx");
        dimensions.put("ip","192.168.1.1");

        Map<String,Double> measures = new HashMap<String, Double>();
        measures.put("lantern",0.5);

        Map<String,String> normalFields = new HashMap<String, String>();
        normalFields.put("message","this is message...");
        normalFields.put("logtime","2019-04-03 09:23:11");

        AvroSerializer avroSerializer = new AvroSerializer(LogAvroMacroDef.metadata);
        byte[] message = avroSerializer.serializingLog(logTypeName, timestamp, source, offset, dimensions, measures, normalFields);
        AvroDeserializer avroDeserializer = AvroDeserializerFactory.getLogsDeserializer();
        GenericRecord deserializing = avroDeserializer.deserializing(message);
        System.out.println(deserializing.toString());

        System.out.println( "Hello World!" );
    }
}
