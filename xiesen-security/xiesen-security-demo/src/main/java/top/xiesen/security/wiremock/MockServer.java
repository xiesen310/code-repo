package top.xiesen.security.wiremock;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @Description MockServer 是模拟http请求的
 * 1. 启动一个 wiremock 服务,通过下面的代码进行修改服务器上的 http 服务
 * 2. 具体可以参考 wiremock 官网 http://wiremock.org
 * @className top.xiesen.security.wiremock.MockServer
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 15:11
 */
public class MockServer {

    public static void main(String[] args) throws Exception {
        /*int num = 3000;
        MockData.generateData(num);
        System.out.println("生产规则 " + num + " 条");
        Thread.sleep(10 * 1000);*/

        configureFor("192.168.1.95", 9999);
        /**
         * 清除之前的所有配置
         */
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
        mock("/v1/alarm_rules", "alarm_rules");

    }

    /**
     * 模拟数据
     *
     * @param url  url 地址
     * @param file 文件名称
     * @throws IOException
     */
    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
    }

}
