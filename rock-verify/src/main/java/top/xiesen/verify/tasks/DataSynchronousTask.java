package top.xiesen.verify.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.xiesen.verify.service.RockLogStructService;

/**
 * @Description 定时将 mysql 中的数据同步到 redis 中
 * @className top.xiesen.verify.tasks.DataSynchronousTask
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/21 0:04
 */
@Component
public class DataSynchronousTask {
    private final Logger logger = LoggerFactory.getLogger(DataSynchronousTask.class);

    @Autowired
    private RockLogStructService rockLogStructService;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        rockLogStructService.mysql2redis();
        logger.info("将 mysql 中的数据同步到 redis 中");
    }


}
