package top.xiesen.verify.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 定时将 mysql 中的数据同步到 redis 中
 * @className top.xiesen.verify.tasks.DataSynchronousTask
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/21 0:04
 */
@Component
public class DataSynchronousTask {

    // todo 数据同步
    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        System.out.println("将 mysql 中的数据同步到 redis 中");
    }
}
