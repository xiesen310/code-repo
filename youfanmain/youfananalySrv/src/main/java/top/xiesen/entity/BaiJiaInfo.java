package top.xiesen.entity;

import java.util.List;

/**
 * @Description 败家实体类
 * @Author 谢森
 * @Date 2019/8/5 15:04
 */
public class BaiJiaInfo {
    private String baiJiaType; // 败家指数区段: 0-20, 20-50, 50-70, 70-80, 80-90, 90-100
    private String userId; // 用户id
    private String createTime; // 创建时间
    private String amount; // 金额
    private String payType; // 支付类型
    private String payTime; // 支付时间
    private String payStatus; // 支付状态 0:未知, 1:已支付, 2:已退款
    private String couponAmount; // 优惠金额
    private String totalAmount; // 总金额
    private String refundAmount; // 退款金额

    private Long count; // 数量
    private String groupField; // 分组字段

    private List<BaiJiaInfo> list;

    public List<BaiJiaInfo> getList() {
        return list;
    }

    public void setList(List<BaiJiaInfo> list) {
        this.list = list;
    }

    public BaiJiaInfo() {
    }

    public BaiJiaInfo(String baiJiaType, String userId, String createTime, String amount,
                      String payType, String payTime, String payStatus, String couponAmount,
                      String totalAmount, String refundAmount, Long count, String groupField) {
        this.baiJiaType = baiJiaType;
        this.userId = userId;
        this.createTime = createTime;
        this.amount = amount;
        this.payType = payType;
        this.payTime = payTime;
        this.payStatus = payStatus;
        this.couponAmount = couponAmount;
        this.totalAmount = totalAmount;
        this.refundAmount = refundAmount;
        this.count = count;
        this.groupField = groupField;
    }

    public String getBaiJiaType() {
        return baiJiaType;
    }

    public void setBaiJiaType(String baiJiaType) {
        this.baiJiaType = baiJiaType;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String toString() {
        return "BaiJiaInfo{" +
                "baiJiaType='" + baiJiaType + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", amount='" + amount + '\'' +
                ", payType='" + payType + '\'' +
                ", payTime='" + payTime + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", couponAmount='" + couponAmount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", refundAmount='" + refundAmount + '\'' +
                ", count=" + count +
                ", groupField='" + groupField + '\'' +
                '}';
    }
}
