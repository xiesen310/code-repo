package top.xiesen.verify.service;

import top.xiesen.verify.pojo.Business;
import top.xiesen.verify.pojo.JsonResult;

import java.util.List;

/**
 * @author 谢森
 */
public interface BusinessService extends BaseCommonService<Business, String> {

    /**
     * saveBusiness
     *
     * @param business
     * @return
     */
    JsonResult saveBusiness(Business business);

    /**
     * findBusinessBySysName
     *
     * @param sysName
     * @return
     */
    List<Business> findBusinessBySysName(String sysName);
}
