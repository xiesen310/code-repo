package top.xiesen.verify.service;

import top.xiesen.verify.exception.DatabaseInsertException;
import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JSONResult;

import java.util.List;

public interface BusinessService extends BaseCommonService<Business, String> {
    JSONResult saveBusiness(Business business);

    List<Business> findBusinessBySysName(String sysName);
}
