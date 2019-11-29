package top.xiesen.rock.common.enums;

/**
 * 用户状态
 *
 * @author 谢森
 */
public enum UserStatus {
    /**
     * 正常
     */
    OK("0", "正常"),
    /**
     * 停用
     */
    DISABLE("1", "停用"),
    /**
     * 删除
     */
    DELETED("2", "删除");

    /**
     * 状态码
     */
    private final String code;

    /**
     * 描述信息
     */
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
