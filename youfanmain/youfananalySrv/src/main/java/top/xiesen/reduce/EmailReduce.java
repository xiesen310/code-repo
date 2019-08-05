package top.xiesen.reduce;

import org.apache.flink.api.common.functions.ReduceFunction;
import top.xiesen.entity.EmailInfo;

/**
 * @Description EmailReduce email运行商 Reduce
 * @Author 谢森
 * @Date 2019/8/5 11:27
 */
public class EmailReduce implements ReduceFunction<EmailInfo> {
    @Override
    public EmailInfo reduce(EmailInfo value1, EmailInfo value2) throws Exception {
        Long count1 = value1.getCount();
        String emailType = value1.getEmailType();
        String groupField = value1.getGroupField();

        Long count2 = value2.getCount();

        EmailInfo emailInfoFinal = new EmailInfo();
        emailInfoFinal.setGroupField(groupField);
        emailInfoFinal.setEmailType(emailType);
        emailInfoFinal.setCount(count1 + count2);

        return emailInfoFinal;
    }
}
