package top.xiesen.verify.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.xiesen.verify.kafka.json.JsonSchema;

import java.io.IOException;
import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@EnableAutoConfiguration
public class JsonSchemaTest {

    @Test
    public void check() {
        String jsonData = new String("{\"foo\":\"1234\"}");
        String jsonSchema = "{\"type\": \"object\", \"properties\" : {\"foo\" : {\"type\" : \"string\"}}}";
        
        boolean check = JsonSchema.check(jsonSchema, jsonData);
        System.out.println(check);
    }
}
