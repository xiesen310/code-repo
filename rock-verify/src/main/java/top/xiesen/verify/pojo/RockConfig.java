package top.xiesen.verify.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * rockConfig.properties 配置文件映射
 */
@Component
@PropertySource(value = "classpath:rockConfig.properties", encoding = "UTF-8")
@Getter
@Setter
public class RockConfig {
    @Value("${rock.verify.kafka.servers}")
    private String kafkaServers;

    @Value("${rock.verify.kafka.batch.size}")
    private Integer kafkaBatchSize;

    @Value("${rock.verify.kafka.topic}")
    private String kafkaTopic;
    
}
