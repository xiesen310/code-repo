package top.xiesen.entity;

/**
 * @Description ResultMessage
 * @Author 谢森
 * @Date 2019/8/6 10:06
 */
public class ResultMessage {
    private String status; // 状态 fail、success
    private String message; // 消息内容

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
