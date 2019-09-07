package top.xiesen.simulation.zork;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.log4j.Logger;

/**
 * KafkaProducerThread
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2019/2/24 16:49.
 */
public class KafkaProducerThread  implements Runnable{
    private static final Logger LOG = Logger.getLogger(KafkaProducerThread.class);
    private KafkaProducer<String,String> producer = null;
    private ProducerRecord<String ,String> record = null;


    public KafkaProducerThread(KafkaProducer<String,String> producer, ProducerRecord<String ,String> record){
        this.producer = producer;
        this.record = record;
    }

    @Override
    public void run() {
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null != e) {
                    LOG.error("Send message occurs exception: " + e);
                }
                if (null != recordMetadata) {
                    LOG.info(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                }
            }
        });
    }




}
