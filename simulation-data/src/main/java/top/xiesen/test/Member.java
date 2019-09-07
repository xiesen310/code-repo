package top.xiesen.test;

import top.xiesen.test.annoation.Constraints;
import top.xiesen.test.annoation.DBTable;
import top.xiesen.test.annoation.SQLInteger;
import top.xiesen.test.annoation.SQLString;

/**
 * @Description Member
 * @Author 谢森
 * @Date 2019/8/17 14:06
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String reference;

    static int memberCount;

    public String getReference() {
        return reference;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return reference;
    }

    public Integer getAge() {
        return age;
    }
}
