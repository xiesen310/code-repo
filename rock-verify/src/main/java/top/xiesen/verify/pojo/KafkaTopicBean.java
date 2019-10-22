package top.xiesen.verify.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaTopicBean {
    private String topicName;       // topic 名称
    private Integer partition;      // partition 分区数量
    private Integer replication;    // replication 副本数量
    private String description;     //描述信息
}
