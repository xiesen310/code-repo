package top.xiesen.schema;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.commons.lang.StringUtils;

/**
 * 将topic和value对应的类对应起来
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/3/7 13:51.
 */
@SuppressWarnings("all")
public enum  TopicEnum {
    Log_AVRO("kafka-topic",new LogAvroBean());

    private String topic;
    private SpecificRecordBase record;

    TopicEnum(String topic, SpecificRecordBase record) {
        this.topic = topic;
        this.record = record;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public SpecificRecordBase getRecord() {
        return record;
    }

    public void setRecord(SpecificRecordBase record) {
        this.record = record;
    }

    public static TopicEnum getTopicEnum(String topicName) {
        if (topicName.isEmpty()) {
            return null;
        }

        for (TopicEnum topicEnum : values()) {
            if (StringUtils.equalsIgnoreCase(topicEnum.getTopic(), topicName)) {
                return topicEnum;
            }
        }
        return null;
    }
}
