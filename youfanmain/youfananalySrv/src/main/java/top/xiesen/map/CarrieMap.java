package top.xiesen.map;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import top.xiesen.entity.CarrieInfo;
import top.xiesen.utils.CarrieUtils;
import top.xiesen.utils.DateUtils;
import top.xiesen.utils.HbaseUtils;

/**
 * @Description 运行商 Map
 * @Author 谢森
 * @Date 2019/7/30 22:21
 */
public class CarrieMap implements MapFunction<String, CarrieInfo> {
    @Override
    public CarrieInfo map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] userinfos = s.split(",");
        String userid = userinfos[0];
        String username = userinfos[1];
        String sex = userinfos[2];
        String telphone = userinfos[3];
        String email = userinfos[4];
        String age = userinfos[5];
        String registerTime = userinfos[6];
        String usertype = userinfos[7]; // 终端类型:0、pc端；1、移动端；2、小程序
        int carrieType = CarrieUtils.getCarrieByTel(telphone);

        /**
         * 0、未知 1、移动 2、联通 3、电信
         */
        String carrieTypeString = carrieType == 0 ? "未知运行商" : carrieType == 1 ? "移动用户" : carrieType == 2 ? "联通用户" : "电信用户";
        System.out.println(carrieTypeString);

        String tableName = "userflaginfo";
        String rowkey = userid;
        String familyName = "baseinfo";
        String colum = "carrieinfo"; // 运行商
        HbaseUtils.putdata(tableName, rowkey, familyName, colum, carrieTypeString);

        CarrieInfo carrieInfo = new CarrieInfo();
        String groupfield = "carrieInfo==" + carrieType;
        carrieInfo.setCarrie(carrieTypeString);
        carrieInfo.setCount(1L);
        carrieInfo.setGroupfield(groupfield);
        return carrieInfo;
    }
}
