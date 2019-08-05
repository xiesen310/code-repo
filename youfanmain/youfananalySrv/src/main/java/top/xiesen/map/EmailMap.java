package top.xiesen.map;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;
import top.xiesen.entity.EmailInfo;
import top.xiesen.utils.EmailUtils;
import top.xiesen.utils.HbaseUtils;

/**
 * @Description email 标签 map 类
 * @Author 谢森
 * @Date 2019/8/5 11:18
 */
public class EmailMap implements MapFunction<String, EmailInfo> {
    @Override
    public EmailInfo map(String s) throws Exception {
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
        String emailType = EmailUtils.getEmailTypeBy(email);

        String tableName = "userflaginfo";
        String rowkey = userid;
        String familyName = "baseinfo";
        String column = "emailType"; // email运行商
        HbaseUtils.putdata(tableName, rowkey, familyName, column, emailType);

        EmailInfo emailInfo = new EmailInfo();
        String groupField = "emailType==" + emailType;
        emailInfo.setEmailType(emailType);
        emailInfo.setCount(1L);
        emailInfo.setGroupField(groupField);
        return emailInfo;
    }
}
