package top.xiesen.security.exception;

/**
 * @Description
 * @className top.xiesen.security.exception.UserNotExistException
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 14:02
 */
public class UserNotExistException extends RuntimeException{
    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
