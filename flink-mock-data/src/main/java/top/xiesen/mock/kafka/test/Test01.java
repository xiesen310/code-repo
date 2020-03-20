package top.xiesen.mock.kafka.test;

/**
 * @Description
 * @className top.xiesen.mock.kafka.test.Test01
 * @Author 谢森
 * @Email xiesen@zork.com.cn
 * @Date 2020/3/16 16:24
 */
public class Test01 {
    public static void main(String[] args) {
        String a = "filter {\n" +
                "  ruby {\n" +
                "    code => \"\n" +
                "     message = event.get('message')\n" +
                "     time = message.split(' ')[0]\n" +
                "     method = message.split(' ')[1]\n" +
                "     type = message.split(' ')[2]\n" +
                "     className = message.split(' ')[3]    \n" +
                "     \n" +
                "     event.set('time',time)\n" +
                "     event.set('method',method)\n" +
                "     event.set('type',type)\n" +
                "     event.set('className',className)\n" +
                "      \n" +
                "    \"\n" +
                "\n" +
                "  }\n" +
                "\n" +
                "}\n";

        System.out.println(a);
    }
}
