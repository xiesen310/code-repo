package top.xiesen.simulation.multithreading;

public class HandlerProducer implements Runnable {
    private String message;
    private String topic;

    public HandlerProducer(String topic, String message) {
        this.message = message;
        this.topic = topic;
    }

    @Override
    public void run() {
        KafkaProducerSingleton kafkaProducerSingleton = KafkaProducerSingleton.getInstance();
        kafkaProducerSingleton.init(topic, 3);
        System.out.println("当前线程:" + Thread.currentThread().getName() + ",获取的kafka实例:" + kafkaProducerSingleton + ", 发送的消息: " + message);
        kafkaProducerSingleton.sendKafkaMessage(message);
    }
}
