package top.xiesen.verify.tasks;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 谢森
 */
@Component
public class TestTask {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled(fixedRate = 3000)
     * @Scheduled(cron = "20-40 * * * * ?")
     */
    public void reportCurrentTime() {
        System.out.println("现在时间: " + DATE_FORMAT.format(new Date()));
    }
}
