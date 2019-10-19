package top.xiesen.verify.service;

import top.xiesen.verify.pojo.Business;

import java.util.List;

public interface BusinessService extends BaseCommonService<Business, String> {
    void saveBusiness(Business business);
    List<Business> findBusinessBySysName(String sysName);
}
