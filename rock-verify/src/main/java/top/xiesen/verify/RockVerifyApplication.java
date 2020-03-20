package top.xiesen.verify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 谢森
 * 扫描所有需要的包，包含一些自用的工具类包 所在路径
 * @EnableScheduling // 开启定时任务
 * @EnableAsync
 */
@SpringBootApplication
@MapperScan(basePackages = "top.xiesen.verify.mapper")
@ComponentScan(basePackages = {"top.xiesen.verify", "org.n3r.idworker"})
@EnableScheduling
public class RockVerifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockVerifyApplication.class, args);
    }

}
