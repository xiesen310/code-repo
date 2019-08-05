package top.xiesen.entity;

/**
 * @Description email 实体类
 * @Author 谢森
 * @Date 2019/8/5 11:19
 */
public class EmailInfo {
    private String emailType; // 邮箱类型
    private Long count; // 数量
    private String groupField; // 分组字段

    public EmailInfo() {
    }

    public EmailInfo(String emailType, Long count, String groupField) {
        this.emailType = emailType;
        this.count = count;
        this.groupField = groupField;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }

    @Override
    public String toString() {
        return "EmailInfo{" +
                "emailType='" + emailType + '\'' +
                ", count=" + count +
                ", groupField='" + groupField + '\'' +
                '}';
    }
}
