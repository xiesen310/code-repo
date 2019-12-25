package top.xiesen.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @className top.xiesen.security.dto.UserQueryCondition
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 11:11
 */
@Data
public class UserQueryCondition {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户年龄起始值")
    private int age;

    @ApiModelProperty(value = "用户年龄终止值")
    private String ageTo;
    private String xxx;
}
