package top.xiesen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description StartUpMain 程序入口函数
 * @Author 谢森
 * @Date 2019/8/6 10:00
 */
@SpringBootApplication
@EnableEurekaClient
public class StartUpMain {
    public static void main(String[] args) {
        SpringApplication.run(StartUpMain.class, args);
    }
}
