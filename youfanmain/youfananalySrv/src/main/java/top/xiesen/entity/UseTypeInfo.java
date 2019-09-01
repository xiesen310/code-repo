package top.xiesen.entity;

/**
 * @Description BrandLike
 * @Author 谢森
 * @Date 2019/8/10 14:51
 */
public class UseTypeInfo {
    private String useType;
    private Long count;
    private String groupFieds;

    public String getGroupFieds() {
        return groupFieds;
    }

    public void setGroupFieds(String groupFieds) {
        this.groupFieds = groupFieds;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
