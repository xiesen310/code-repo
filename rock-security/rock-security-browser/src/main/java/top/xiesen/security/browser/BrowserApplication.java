package top.xiesen.security.browser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BrowserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrowserApplication.class, args);
    }

}
