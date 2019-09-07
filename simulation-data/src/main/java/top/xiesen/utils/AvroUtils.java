package top.xiesen.utils;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import top.xiesen.schema.LogAvroBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * todo
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/4/3 10:23.
 */
public class AvroUtils {
    public static byte[] serializeLogBean(LogAvroBean logAvroBean) throws IOException {
        DatumWriter<LogAvroBean> logAvroBeanDatumWriter = new SpecificDatumWriter<>(LogAvroBean.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream,null);
        logAvroBeanDatumWriter.write(logAvroBean,binaryEncoder);
        return outputStream.toByteArray();
    }
    public static LogAvroBean deserializeLogBean(byte[] data) throws IOException {
        DatumReader<LogAvroBean> logAvroBeanDatumReader = new SpecificDatumReader<>(LogAvroBean.class);
        BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(data),null);
        return logAvroBeanDatumReader.read(new LogAvroBean(),binaryDecoder);
    }
}
