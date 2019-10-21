package top.xiesen;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (i == 1) {
                    break;
                } else {
                    System.out.println("外层循环，第 " + j + " 次; 内层循环，第 " + i + " 次");
                }
            }
        }
    }
}
