package top.xiesen.simulation.fk;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import top.xiesen.simulation.fk.utils.KafkaUtils;

import java.util.Random;

/**
 * 模拟 委托数据流 Delegate
 *
 * @version V1.0
 * @Author XieSen
 * @Date 2018/6/2 13:28.
 */
public class DelegateStream {
    private KafkaProducer<String, String> producer;
    Random random = new Random();

    public DelegateStream() {
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
     * 委托数据流消息体
     *
     * @return
     * @throws JSONException
     */
    public String message() throws JSONException {
        JSONObject event = new JSONObject();
        int[] custids = {101, 102, 103, 104, 105};
        event.put("serverid", random.nextInt(10))
                .put("ordersno", random.nextInt(10))
                .put("ordergroup", random.nextInt(10))
                .put("orderid", "12" + random.nextInt(10))
                .put("orderdate", KafkaUtils.getNowDateLong())
                .put("operdate", KafkaUtils.getNowDateLong())
                .put("opertime", KafkaUtils.getNowTimeLong())
//                .put("custid", custids[random.nextInt(custids.length)])
                .put("custid", 101)
                .put("custkind", "AB")
                .put("custgroup", "AB")
                .put("custname", "ABC")
                .put("orgid", "ab")
                .put("brhid", "bb")
                .put("fundid", random.nextInt(10000))
                .put("moneytype", "cn")
                .put("fundkind", "1" + random.nextInt(10))
                .put("fundlevel", "as")
                .put("fundgroup", "ss")
                .put("secuid", "32323" + random.nextInt(10))
                .put("rptsecuid", "23232" + random.nextInt(10))
                .put("market", random.nextInt(10))
                .put("seat", "02" + random.nextInt(10))
                .put("stkcode", "600121")
                .put("stkname", "test")
                .put("stktype", "s")
                .put("orderprice", Double.parseDouble(KafkaUtils.formatDouble(random.nextDouble() * 20)))
                .put("bondintr", 0)
                .put("orderqty", random.nextInt(1000))
                .put("reportqty", random.nextInt(1000))
                .put("fundavl", random.nextInt(10000))
                .put("matchqty", random.nextInt(10))
                .put("cancelqty", 0)
                .put("tradefee", 0)
                .put("orderfrzamt", 0)
                .put("clearamt", 0)
                .put("matchamt", random.nextInt(1000))
                .put("ordertype", 0)
                .put("nightflag", 0)
                .put("bsflag", "A")
                .put("inputbs", "B")
                .put("cancelflag", "F")
                .put("combtype", 0)
                .put("reporttime", KafkaUtils.getNowDateInteger())
                .put("orderstatus", 0)
                .put("recnum", -1)
                .put("sourcetype", "0")
                .put("bankcode", "0011")
                .put("bankorderid", "00112")
                .put("bankid", "A1A4A4")
                .put("bankfrzsno", "1S1F5S")
                .put("extdbfsno", random.nextInt(1000))
                .put("agentid", random.nextInt(1000))
                .put("operid", random.nextInt(1000))
                .put("operlevel", "F")
                .put("operorg", "WS")
                .put("netaddr", "192.168.1." + random.nextInt(255))
                .put("operway", "W")
                .put("remark", "QWGQWG")
                .put("stkdiff", random.nextInt(1000))
                .put("creditfrzqty", random.nextInt(1000))
                .put("creditfrzamt", Double.parseDouble(KafkaUtils.formatDouble(random.nextDouble() * 300)))
                .put("fundmarginfrz", Double.parseDouble(KafkaUtils.formatDouble(random.nextDouble() * 200)))
                .put("funddebtsfrz", Double.parseDouble(KafkaUtils.formatDouble(random.nextDouble() * 200)))
                .put("credittype", "0")
                .put("creditdigestid", "ASD")
                .put("oppsecuid", "TJTJ")
                .put("oppseat", "DGJ")
                .put("extamt1", 0)
                .put("extqty1", 0)
                .put("extkind1", "")
                .put("extkind2", "")
                .put("extsecuid1", "")
                .put("extchar32", "");

        return event.toString();
    }

    public static void main(String[] args) throws JSONException, InterruptedException {
        DelegateStream delegate = new DelegateStream();
        while (true) {
            System.out.println(delegate.message());
            delegate.sendRecorder("delegate", "key", delegate.message());
            Thread.sleep(1000);
        }
    }
}
