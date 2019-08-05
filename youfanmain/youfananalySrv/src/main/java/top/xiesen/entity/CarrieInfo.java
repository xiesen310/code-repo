package top.xiesen.entity;

/**
 * @Description 手机运行商实体类
 * @Author 谢森
 * @Date 2019/7/30 22:22
 */
public class CarrieInfo {
    private String carrie; // 运行商
    private Long count; // 数量
    private String groupfield; // 分组字段

    public CarrieInfo() {
    }

    public CarrieInfo(String carrie, Long count, String groupfield) {
        this.carrie = carrie;
        this.count = count;
        this.groupfield = groupfield;
    }

    public String getCarrie() {
        return carrie;
    }

    public void setCarrie(String carrie) {
        this.carrie = carrie;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupfield() {
        return groupfield;
    }

    public void setGroupfield(String groupfield) {
        this.groupfield = groupfield;
    }

    @Override
    public String toString() {
        return "CarrieInfo{" +
                "carrie='" + carrie + '\'' +
                ", count=" + count +
                ", groupfield='" + groupfield + '\'' +
                '}';
    }
}
