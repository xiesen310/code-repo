package top.xiesen.rock.admin.utils;

import top.xiesen.rock.admin.model.dto.RespDto;
import top.xiesen.rock.common.constant.CodeTable;

public class RespHelper {
    public static <T> RespDto<T> createEmptyRespDto() {
        return new RespDto<>();
    }

    public static <T> RespDto<T> fail(int code) {
        RespDto<T> t = createEmptyRespDto();
        t.setCode(code);
        t.setMsg(CodeTable.getDescription(code));
        return t;
    }

    public static <T> RespDto<T> fail(int code, String message) {
        RespDto<T> t = createEmptyRespDto();
        t.setCode(code);
        t.setMsg(CodeTable.getDescription(code) + ":" + message);
        return t;
    }

    public static <T> RespDto<T> fail(int code, T data, String message) {
        RespDto<T> t = createEmptyRespDto();
        t.setCode(code);
        t.setData(data);
        t.setMsg(CodeTable.getDescription(code) + ":" + message);
        return t;
    }

    public static <T> RespDto<T> ok(T data) {
        RespDto<T> t = createEmptyRespDto();
        t.setCode(CodeTable.SUCCESS);
        t.setData(data);
        return t;
    }

    public static <T> RespDto<T> ok(T data, String message) {
        RespDto<T> t = createEmptyRespDto();
        t.setCode(CodeTable.SUCCESS);
        t.setData(data);
        t.setMsg(message);
        return t;
    }
}
