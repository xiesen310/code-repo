package top.xiesen.entity;

/**
 * @Description 年代标签实体类
 * @Author 谢森
 * @Date 2019/7/28 19:37
 */
public class YearBase {
    private String yearType; //  年代类型
    private Long count; // 数量
    private String groupfield; // 分组字段

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }

    public String getYearType() {
        return yearType;
    }

    public void setYearType(String yearType) {
        this.yearType = yearType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "YearBase{" +
                "yearType='" + yearType + '\'' +
                ", count=" + count +
                ", groupfield='" + groupfield + '\'' +
                '}';
    }
}
