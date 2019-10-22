package top.xiesen.verify.json;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JsonSchema
 */
public class JsonSchema {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonSchema.class);

    /**
     * 数据校验
     *
     * @param jsonSchema schema 字符串
     * @param jsonData json 数据
     * @return boolean
     */
    public static boolean check(String jsonSchema, String jsonData) {
        JSONObject Schema = new JSONObject(jsonSchema);
        JSONObject data = new JSONObject(jsonData);
        org.everit.json.schema.Schema schema = SchemaLoader.load(Schema);
        try {
            schema.validate(data);
            return true;
        } catch (ValidationException e) {
            LOGGER.error(String.valueOf(e.getAllMessages()));
            return false;
        }
    }
}
