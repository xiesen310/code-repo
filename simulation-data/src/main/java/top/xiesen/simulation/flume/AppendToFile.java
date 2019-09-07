package top.xiesen.simulation.flume;

import java.io.*;
import java.util.List;
import java.util.Vector;

public class AppendToFile {
    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件复制
     * @param srcFile 源文件
     * @param descFile 目标文件
     */
    public static void fileCopy(String srcFile, String descFile) {
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            InputStreamReader isr = new InputStreamReader(fis, "gbk");
            BufferedReader br = new BufferedReader(isr);

            File newfile = new File(descFile);
            FileOutputStream fos = new FileOutputStream(newfile);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
            BufferedWriter bw = new BufferedWriter(osw);

            String str;
            String[] line;
            str = br.readLine();
            int temp;
            while (str != null) {
                bw.write(str + "\r\n");
                str = br.readLine();
//                Thread.sleep(100);
            }
            br.close();
            isr.close();
            fis.close();
            bw.close();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
//        String fileName = "D:\\temp\\log\\newTemp.txt";
//        String content = "new append! error_pathinfo";
//        按方法A追加文件
//        AppendToFile.appendMethodA(fileName, content);
//        AppendToFile.appendMethodA(fileName, "append end. \n");

//        AppendToFile.appendMethodB(fileName, content);
//        AppendToFile.appendMethodB(fileName, "append end. \n");

        String srcFile = "D:\\temp\\log\\logfile_20181120_根网UFX.log";
        String descFile = "D:\\temp\\log\\newTemp.txt";
        fileCopy(srcFile,descFile);
    }

}
