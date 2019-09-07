package top.xiesen.test;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @Description 反射操作注解
 * @Author 谢森
 * @Date 2019/8/17 14:39
 */
public class TestAnnoation {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("top.xiesen.test.Student");

        // 通过反射获取注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 通过反射获取注解 value 的值
        TableXiesen tableXiesen = (TableXiesen)c1.getAnnotation(TableXiesen.class);
        String value = tableXiesen.value();
        System.out.println(value);

        // 获得类指定的注解
        Field field = c1.getDeclaredField("id");
        FieldXiesen annotation = field.getAnnotation(FieldXiesen.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());


    }
}

@TableXiesen("db_student")
class Student {
    @FieldXiesen(columnName = "db_id", type = "int", length = 10)
    private int id;

    @FieldXiesen(columnName = "db_age", type = "int", length = 10)
    private int age;

    @FieldXiesen(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Student() {
    }

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

/**
 * 类名的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableXiesen {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldXiesen {
    String columnName();

    String type();

    int length();
}



