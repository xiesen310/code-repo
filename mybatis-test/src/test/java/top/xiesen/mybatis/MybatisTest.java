package top.xiesen.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.platform.commons.function.Try;
import top.xiesen.mybatis.bean.Employee;
import top.xiesen.mybatis.dao.EmployeeMapper;
import top.xiesen.mybatis.utils.SqlSessionFactoryUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description MybatisTest
 * @Author 谢森
 * @Date 2019/8/17 18:22
 */
public class MybatisTest {

    /**
     * 1. 根据 xml 配置全局配置文件，创建 sqlSessionFactory 对象
     * 2. sql 映射文件: 配置了每一个 SQL ，以及 SQL 的封装规则等
     * 3. 将 SQL 映射文件 注册到全局配置文件中
     * 4. 写代码
     * 1） 根据全局配置文件获取到 sqlSessionFactory
     * 2） 使用 sqlSessionFactory 获取 SqlSession 对象进行增删改查
     * 一个 SqlSession 就是代表和数据库的一次会话，用完关闭
     * 3） 使用 sql 的唯一标识来告诉 Mybatis 执行那个 sql ，sql 是保存在 sql 映射文件中
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取 SqlSession 实例， 能直接执行已经映射的 SQL 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * sql 唯一标识: the returned list element type
         * 执行 sql 需要的参数: Unique identifier matching the statement to use.
         */
        try {
            Employee employee = sqlSession.selectOne("top.xiesen.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        // 1. 获取 sqlSessionFactory 对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        // 2. 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取接口的实现类对象
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpbyId(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }
}
