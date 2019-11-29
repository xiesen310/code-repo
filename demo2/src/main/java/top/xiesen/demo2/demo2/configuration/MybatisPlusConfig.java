package top.xiesen.demo2.demo2.configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author 谢森
 * @Description Mybatis plus 配置类
 * @className top.xiesen.demo2.demo2.configuration.MybatisPlusConfig
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/26 13:32 星期二
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 添加逻辑删除插件
     * 3.1.0 以上版本，可省略此步骤
     *
     * @return ISqlInjector
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }


    /**
     * 添加乐观锁插件
     *
     * @return OptimisticLockerInterceptor
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 性能分析插件，生产环境上不能开启
     *
     * @return PerformanceInterceptor
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        interceptor.setFormat(true);
        return interceptor;
    }

}
