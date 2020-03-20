package top.xiesen.verify.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 谢森
 */
@Getter
@Setter
@ToString
public class KafkaTopicBean {
    /**
     * topic 名称
     */
    private String topicName;

    /**
     * partition 分区数量
     */
    private Integer partition;

    /**
     * replication 副本数量
     */
    private Integer replication;

    /**
     * 描述信息
     */
    private String description;
}
