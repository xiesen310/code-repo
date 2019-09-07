package top.xiesen.schema;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 实现自己的分区逻辑
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/3/7 14:07.
 */
public class AvroPartition implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (key == null) {
            return 0;
        }
        String partitionKey = key.toString();
        try {
            //根据key的最后一位和partitions取模，设置分区
            int partitionID = Integer.valueOf(partitionKey.substring(partitionKey.length()-2)) % 6;
            System.out.println(partitionID);

            return partitionID;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
