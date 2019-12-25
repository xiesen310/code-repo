package top.xiesen.security.browser.support;

import lombok.Data;

/**
 * @Description
 * @className top.xiesen.security.browser.support.SimpleResponse
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/22 0:42
 */
@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
