package top.xiesen.verify.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @className top.xiesen.verify.utils.RedisUtils
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/20 23:14
 */

@Service
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void save(String key, String value) {
        String values = redisTemplate.opsForValue().get(key);
        if (null == values) {
            redisTemplate.opsForValue().set(key, value);
        }
    }
}
