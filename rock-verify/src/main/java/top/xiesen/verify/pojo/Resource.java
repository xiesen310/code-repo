package top.xiesen.verify.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:resource.properties", encoding = "UTF-8")
@Getter
@Setter
public class Resource {
    @Value("${top.xiesen.verify.name}")
    private String name;

    @Value("${top.xiesen.verify.website}")
    private String website;

    @Value("${top.xiesen.verify.language}")
    private String language;

}
