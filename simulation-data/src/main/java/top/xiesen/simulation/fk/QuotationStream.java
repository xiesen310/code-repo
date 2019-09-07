package top.xiesen.simulation.fk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import top.xiesen.simulation.fk.utils.KafkaUtils;

import java.util.Random;

/**
 * 模拟行情数据流
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/6/2 12:29.
 */
public class QuotationStream {
    private KafkaProducer<String, String> producer;

    public QuotationStream() {
        producer = new KafkaProducer<>(KafkaUtils.load());
    }

    /**
     * kafka发送消息
     *
     * @param topic
     * @param key
     * @param value
     */
    public void sendRecorder(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
    }

    /**
     * 行情数据流消息体
     *
     * @return
     * @throws JSONException
     */
    public String message() throws JSONException {
        JSONObject event = new JSONObject();
        JSONArray uBidPrice = new JSONArray();
        JSONArray uBidVol = new JSONArray();
        JSONArray uAskPrice = new JSONArray();
        JSONArray uAskVol = new JSONArray();
        Random random = new Random();
        uBidPrice.put(89300)
                .put(89200)
                .put(89100)
                .put(89000)
                .put(88900)
                .put(88800)
                .put(88700)
                .put(88600)
                .put(88500)
                .put(88400);
        uBidVol.put(484900)
                .put(696300)
                .put(372200)
                .put(523900)
                .put(160900)
                .put(162200)
                .put(53900)
                .put(103000)
                .put(211300)
                .put(41000);
        uAskPrice.put(89400)
                .put(89500)
                .put(89600)
                .put(89700)
                .put(89800)
                .put(89900)
                .put(90000)
                .put(90100)
                .put(90200)
                .put(90300);
        uAskVol.put(29700)
                .put(13801)
                .put(41200)
                .put(135400)
                .put(226900)
                .put(118436)
                .put(195618)
                .put(28400)
                .put(56100)
                .put(319441);

        event.put("szType", "L2")
                .put("nMarketId", random.nextInt(9))
                .put("sCode", "600121")
                .put("uDate", KafkaUtils.getNowDateInteger())
                .put("uTime", KafkaUtils.getNowTimeInteger())
                .put("nStatus", 0)
                .put("uPreClose", random.nextDouble() * 100)
                .put("uOpen", 0)
                .put("uHigh", 0)
                .put("uLow", 0)
                .put("uMatch", random.nextInt(1000) + 100)
                .put("uBidPrice", uBidPrice)
                .put("uBidVol", uBidVol)
                .put("uAskPrice", uAskPrice)
                .put("uAskVol", uAskVol)
                .put("uNumTrades", 0)
                .put("iVolume", 0)
                .put("iTurnover", 0)
                .put("iTotalBidVol", random.nextInt(10000))
                .put("iTotalAskVol", random.nextInt(10000))
                .put("uWeightedAvgBidPrice", 0)
                .put("uWeightedAvgAskPrice", 0)
                .put("nIOPV", 0)
                .put("nYieldToMaturity", 0)
                .put("uHighLimited", 0)
                .put("uLowLimited", 0)
                .put("sPrefix", "")
                .put("nSyl1", 0)
                .put("nSyl2", 0)
                .put("nSD2", 0)
                .put("sTradingPhraseCode", "");
        return event.toString();
    }

    public static void main(String[] args) throws JSONException, InterruptedException {
        QuotationStream quotation = new QuotationStream();
        while (true) {
            System.out.println(quotation.message());
            quotation.sendRecorder("quotation", "key", quotation.message());
            Thread.sleep(1000);
        }
    }
}
