package top.xiesen.verify.kafka;

public class ProducerPool implements Cloneable {
    private Producer[] pool;
    private int threadNum = 15;
    // 轮循id
    private int index = 0;

    private static ProducerPool _interance = null;

    public static ProducerPool getInstance() {
        if (_interance == null) {
            _interance = new ProducerPool();
        }
        return _interance;
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
        if (index > 65535) {
            index = 0;
        }
        return pool[index++ % threadNum];
    }


}
