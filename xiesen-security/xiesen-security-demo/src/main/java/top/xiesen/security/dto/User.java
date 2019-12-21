package top.xiesen.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Description
 * @className top.xiesen.security.dto.User
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/12/21 9:59
 */
@Data
public class User {
    public interface UserSimpleView { };
    public interface UserDetailView extends UserSimpleView { };

    @ApiModelProperty("用户 ID")
    @JsonView(UserSimpleView.class)
    private String id;

    @ApiModelProperty("用户名")
    @JsonView(UserSimpleView.class)
    private String username;

    @ApiModelProperty("用户密码")
    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("用户生日")
    @Past(message = "生日必须是过去的时间")
    @JsonView(UserSimpleView.class)
    private Date birthday;
}
