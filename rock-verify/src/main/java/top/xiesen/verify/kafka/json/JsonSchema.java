package top.xiesen.verify.kafka.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * JsonSchema
 */
public class JsonSchema {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSchema.class);

    /**
     * 数据校验
     *
     * @param jsonSchema schema 字符串
     * @param jsonData   json 数据
     * @return boolean
     */
    public static boolean check(String jsonSchema, String jsonData) {
        ProcessingReport report = null;
        try {
            JsonNode data = JsonLoader.fromString(jsonData);
            JsonNode schema = JsonLoader.fromString(jsonSchema);
            report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (report != null) {
                Iterator<ProcessingMessage> it = report.iterator();
                while (it.hasNext()) {
                    LOGGER.error(it.next().toString());
                }
            }

        }
        return report.isSuccess();
    }
}
