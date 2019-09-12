package top.xiesen.rock.admin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户 model 类
 */
@Getter
@Setter
@ToString
public class User {
    private Integer userId;
    private Integer deptId;
    private String loginName;
    private String userName;
    private String email;
    private String phoneNumber;
    private char sex;
    private String avatar;
    private String password;
    private Date loginDate;
    private String remark;

}
