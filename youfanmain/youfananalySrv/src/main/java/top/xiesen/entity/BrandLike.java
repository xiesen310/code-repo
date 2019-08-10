package top.xiesen.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description BrandLike
 * @Author 谢森
 * @Date 2019/8/10 14:51
 */
public class BrandLike {
    private String brand;
    private Long count;
    private String groupFieds;

    public String getGroupFieds() {
        return groupFieds;
    }

    public void setGroupFieds(String groupFieds) {
        this.groupFieds = groupFieds;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
