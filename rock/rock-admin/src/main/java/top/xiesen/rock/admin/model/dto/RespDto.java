package top.xiesen.rock.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回给请求方的响应通用结构DataTransferObject(DTO)
 */
@Getter
@Setter
public class RespDto<T> {
    /**
     * 错误码
     */
    public int code;
    /**
     * 错误描述
     */
    public String msg;

    /**
     * 此项当有值时才会返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
