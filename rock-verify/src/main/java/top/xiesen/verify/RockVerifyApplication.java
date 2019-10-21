package top.xiesen.verify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "top.xiesen.verify.mapper") // 扫描 mybatis mapper 包路径
// 扫描所有需要的包，包含一些自用的工具类包 所在路径
@ComponentScan(basePackages = {"top.xiesen.verify","org.n3r.idworker"})
//@EnableScheduling // 开启定时任务
//@EnableAsync
public class RockVerifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RockVerifyApplication.class, args);
    }

}
