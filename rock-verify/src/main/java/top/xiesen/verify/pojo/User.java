package top.xiesen.verify.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private String name;

    @JsonIgnore
    private String password;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date birthday;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;
}
