package top.xiesen.demo2.demo2.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author 谢森
 * @Description 自定义自动填充元数据处理
 * @className top.xiesen.demo2.demo2.component.MyMetaObjectHandler
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/29 14:23 星期五
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * fieldName 指的是实体类中的属性名
     * 优化思路: 判断是否有需要填充的字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            System.out.println("insertFill ~~");
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    /**
     * 优化思路: 如果已经设置了更新时间，使用设置的时间，如果没有设置，自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            System.out.println("updateFill ~~");
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
