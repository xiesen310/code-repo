package top.xiesen.mybatis.dao;

import top.xiesen.mybatis.bean.Employee;

/**
 * @Description EmployeeMapper
 * @Author 谢森
 * @Date 2019/8/17 18:41
 */
public interface EmployeeMapper {
    public Employee getEmpbyId(Integer id);
}
