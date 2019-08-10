package top.xiesen.log;

import java.io.Serializable;

/**
 * @Description ScanProductLog 浏览商品日志结构
 * @Author 谢森
 * @Date 2019/8/5 23:59
 */
public class ScanProductLog implements Serializable {
    private static final long serialVersionUID = -5809613782380626847L;
    private int productId; // 商品id
    private int productTypeId; // 商品类别id
    private String scanTime; // 浏览时间
    private String stayTime; // 停留时间
    private int userId; // 用户id
    private int userType; // 终端类型:0、pc端；1、移动端；2、小程序
    private String userIp; // 用户ip

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

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
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
