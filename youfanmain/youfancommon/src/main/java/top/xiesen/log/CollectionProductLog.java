package top.xiesen.log;

import java.io.Serializable;

/**
 * @Description CollectionProductLog 收集商品日志类型
 * @Author 谢森
 * @Date 2019/8/6 0:05
 */
public class CollectionProductLog implements Serializable {
    private static final long serialVersionUID = 974732168361263581L;
    private int productId; // 商品id
    private int productTypeId; // 商品类别id
    private String operatorTime; // 操作时间
    private int operatorType; // 操作类型 0：收藏，1：取消
    private int userId; // 用户id
    private int userType; // 终端类型:0、pc端；1、移动端；2、小程序
    private String userIp; // 用户ip
    private String brand; //  品牌

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(String operatorTime) {
        this.operatorTime = operatorTime;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
}
