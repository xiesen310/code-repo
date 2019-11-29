package com.example.demo1;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.example.demo1.dao.UserMapper;
import com.example.demo1.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 查询测试
 * @className com.example.demo1.SearchTest
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2019/11/25 14:32 星期一
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    public void selectIds() {
        List<Long> idsList = Arrays.asList(1094590409767661570L,
                1094592041087729666L, 1088248166370832385L);
        userMapper.selectBatchIds(idsList);
    }

    @Test
    public void selectMap() {
        /**
         * map 中的 key 是数据库中的列名，不是实体中的字段名
         * columnMap.put("name","王天风");
         * columnMap.put("age",25);
         * where name = "王天风" and "age" = 25
         */
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "王天风");
        columnMap.put("age", 25);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.like("name", "雨")
                .lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨年并且龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨")
                .between("age", 20, 40)
                .isNotNull("email");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .or().gt("age", 25)
                .orderByDesc("age")
                .orderByAsc("id");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
                .inSql("manager_id", "select id from user where name like '王%'");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 40)
                        .or()
                        .isNotNull("email")
                );

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王")
                .or(
                        wq -> wq.lt("age", 40)
                                .gt("age", 20)
                                .isNotNull("email")
                );
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35))
                .last("limit 1");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)
     * 第一种情况：select id,name
     * from user
     * where name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapperSupper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name")
                .like("name", "雨")
                .lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40(需求1加强版)
     * 第二种情况：select id,name,age,email
     * * from user
     * * where name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapperSupper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨")
                .lt("age", 40)
                .select(User.class, info -> !info.getColumn().equals("create_time")
                        && !info.getColumn().equals("manager_id")
                );
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        String name = "";
        String email = "@";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*if (StringUtils.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (StringUtils.isNotEmpty(email)) {
            queryWrapper.like("email", email);
        }*/

        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 接收实体参数
     */
    @Test
    public void selectByWrapperEntity() {
        User whereUser = new User();
        whereUser.setName("刘红雨");
        whereUser.setAge(32);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * allEq(Map<R, V> params)
     * allEq(Map<R, V> params, boolean null2IsNull)
     * allEq(boolean condition, Map<R, V> params, boolean null2IsNull)
     */
    @Test
    public void selectByWrapperAllEq() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);
        // queryWrapper.allEq(params,false);
        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "雨")
                .lt("age", 40);

        List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     * 并且只取年龄总和小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * from user
     * group by manager_id
     * having sum(age) <500
     */
    @Test
    public void selectByWrapperMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("manager_id")
                .having("sum(age)<{0}", 500);

        List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name").like("name", "雨")
                .lt("age", 40);

        List<Object> mapList = userMapper.selectObjs(queryWrapper);
        mapList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨")
                .lt("age", 40);
        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("总记录数: " + count);
    }

    @Test
    public void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "刘红雨")
                .lt("age", 40);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void selectByWrapperLambda() {
        // LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        // LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.like(User::getName, "雨").lt(User::getAge, 40);
        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapperLambda2() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.likeRight(User::getName, "王")
                .and(lqw -> lqw.lt(User::getAge, 40)
                        .or()
                        .isNotNull(User::getEmail)
                );
        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByWrapperLambda3() {
        List<User> list = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName, "雨").ge(User::getAge, 20).list();
        list.forEach(System.out::println);
    }

    @Test
    public void selectMy() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.likeRight(User::getName, "王")
                .and(lqw -> lqw.lt(User::getAge, 40)
                        .or()
                        .isNotNull(User::getEmail)
                );
        List<User> userList = userMapper.selectAll(lambdaQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.ge("age", 26);
        /**
         * long current 当前页
         * long size 一页显示多少
         * boolean isSearchCount 是否查询总记录数，默认true
         */
        Page<User> page = new Page<>(1, 2, false);
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数: " + iPage.getPages());
        System.out.println("总记录数: " + iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectPageMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.ge("age", 26);
        Page<User> page = new Page<>(1, 2);
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
        System.out.println("总页数: " + iPage.getPages());
        System.out.println("总记录数: " + iPage.getTotal());
        List<Map<String, Object>> mapList = iPage.getRecords();
        mapList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectMyPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age", 26);
        Page<User> page = new Page<>(1, 2);

        IPage<User> iPage = userMapper.selectUserPage(page, queryWrapper);
        System.out.println("总页数: " + iPage.getPages());
        System.out.println("总记录数: " + iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }
}
