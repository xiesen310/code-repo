package top.xiesen.verify.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理状态码定义基本类
 * @author 谢森
 */
public class CodeTable {
    public static final Map<Integer, String> CODE_TABLE = new HashMap<>(1000);

    /**
     * 数据库相关 60000-60099
     */
    public final static int DATABASE_INSERT_SUCCESS = 60000;
    public final static int DATABASE_INSERT_FAILURE = 60001;

    public final static int UPDATE_INSERT_SUCCESS = 60000;
    public final static int UPDATE_INSERT_FAILURE = 60001;


    /**
     * 参数相关 600100 - 600199
     */
    public final static int PARAM_ERR = 600100;
    public final static int PARAM_MISSING_ERR = 600101;
    public final static int PARAM_FORMAT_ERR = 600102;
    public final static int PARAM_JSON_ERR = 600103;

    static {
        CODE_TABLE.put(DATABASE_INSERT_SUCCESS, "数据库插入成功");
        CODE_TABLE.put(DATABASE_INSERT_FAILURE, "数据库插入失败");
        CODE_TABLE.put(UPDATE_INSERT_SUCCESS, "数据库更新成功");
        CODE_TABLE.put(UPDATE_INSERT_FAILURE, "数据库更新失败");
    }
}
