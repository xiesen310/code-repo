package top.xiesen.simulation.fk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import top.xiesen.simulation.fk.utils.KafkaUtils;

import java.util.Random;

/**
 * 模拟风控成交表数据流
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/6/2 10:35.
 */
public class MatchStream {

    private KafkaProducer<String, String> producer;

    public MatchStream() {
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
     * 成交表数据流消息体
     *
     * @return
     * @throws JSONException
     */
    public String message() throws JSONException {
        JSONObject event = new JSONObject();
        JSONObject content = new JSONObject();
        JSONArray target = new JSONArray();
        Random random = new Random();
        int matchqty = random.nextInt(100) + 10;
        Double matchprice = Double.valueOf(KafkaUtils.formatDouble(random.nextDouble() * 20));

        content.put("serverid", random.nextInt(9))
                .put("matchsno", random.nextInt(9))
                .put("trddate", KafkaUtils.getNowDateLong())
                .put("matchtime", KafkaUtils.getNowTimeLong())
                .put("custid", 101)
                .put("orgid", "1" + random.nextInt(1000))
                .put("fundid", random.nextInt(10000))
                .put("secuid", "A2800412" + random.nextInt(9))
                .put("bsflag", "AB")
                .put("matchtype", "0")
                .put("moneytype", "0")
                .put("market", "1")
                .put("stkcode", "600121")
                .put("stktype", "0")
                .put("matchqty", matchqty)
                .put("matchprice", matchprice)
                .put("matchamt", Double.parseDouble(KafkaUtils.formatDouble(matchprice * matchqty)));

        target.put("")
                .put("");

        event.put("SRC", "23")
                .put("MSG_TYPE", "JZJY_MSG")
                .put("MSG_SUBTYPE", "MATCH")
                .put("SEND_DATE", KafkaUtils.getNowDateInteger())
                .put("SEND_TIME", KafkaUtils.getNowTimeInteger())
                .put("MSG_ID", "8223945")
                .put("target", target)
                .put("content", content);

        return event.toString();
    }

    public static void main(String[] args) throws JSONException, InterruptedException {
        MatchStream ms = new MatchStream();
        while (true) {
            System.out.println(ms.message());
            ms.sendRecorder("match", "key", ms.message());
            Thread.sleep(1000);
        }

    }

}
