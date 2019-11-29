package top.xiesen.rock.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理状态码定义基本类
 * @author 谢森
 */
public class CodeTable {
    public static final Map<Integer, String> CODE_TABLE = new HashMap<>(1000);
    /**
     * 通用模块
     */
    public final static int SUCCESS = 0;
    public final static int FAILURE = 1;

    /**
     * 参数相关
     */
    public final static int PARAM_ERR = 100100;
    public final static int PARAM_FORMAT_ERR = 100101;
    public final static int PARAM_JSON_ERR = 100102;


    static {
        CODE_TABLE.put(SUCCESS, "正常");
        CODE_TABLE.put(FAILURE, "未知异常");

        /**
         * 100100-100199的错误码为输入参数相关问题
         */
        CODE_TABLE.put(PARAM_ERR, "参数异常");
        CODE_TABLE.put(PARAM_FORMAT_ERR, "参数格式错误");
        CODE_TABLE.put(PARAM_JSON_ERR, "Json参数解析失败");
    }

    /**
     * 获取对用 code 的描述信息
     *
     * @param code 错误码
     * @return 错误描述信息
     */
    public static String getDescription(Integer code) {
        return CODE_TABLE.get(code);
    }
}
