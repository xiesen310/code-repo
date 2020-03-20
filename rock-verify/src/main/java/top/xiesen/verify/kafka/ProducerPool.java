package top.xiesen.verify.kafka;

/**
 * @author 谢森
 */
public class ProducerPool implements Cloneable {
    private Producer[] pool;
    private int threadNum = 15;
    /**
     * 轮循id
     */
    private int index = 0;
    int maxNum = 65535;

    private static ProducerPool instance = null;

    public static ProducerPool getInstance() {
        if (instance == null) {
            instance = new ProducerPool();
        }
        return instance;
    }

    private ProducerPool() {
        init();
    }

    /**
     * 初始化生产者线程池
     */
    public void init() {
        pool = new Producer[threadNum];
        for (int i = 0; i < threadNum; i++) {
            pool[i] = new Producer();
        }
    }

    public Producer getProducer() {
        if (index > maxNum) {
            index = 0;
        }
        return pool[index++ % threadNum];
    }

}
