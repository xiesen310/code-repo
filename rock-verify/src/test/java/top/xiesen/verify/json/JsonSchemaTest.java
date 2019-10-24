package top.xiesen.verify.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xiesen.verify.kafka.json.JsonSchema;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class JsonSchemaTest {

    @Test
    public void check() {
        String jsonSchema = "{\"type\":\"object\",\"properties\":{\"foo\":{\"type\":\"string\"}}}";
        String jsonData = "{\"foo\":123}";
        JsonSchema.check(jsonSchema, jsonData);
    }
}
