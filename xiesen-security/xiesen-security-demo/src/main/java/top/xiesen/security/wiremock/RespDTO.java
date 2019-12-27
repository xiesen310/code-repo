package top.xiesen.security.wiremock;

import lombok.Data;

@Data
public class RespDTO {
    private String code;
    private String message;
    private Object data;
}
