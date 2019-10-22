package top.xiesen.verify.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {
    /**
     * 根据文件名获取该properties对象
     *
     * @param propertieFileName
     * @return
     */
    public static Properties getProperties(String propertieFileName) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            inputStream = PropertiesUtil.class.getResourceAsStream(propertieFileName);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(inputStreamReader);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Exception ex) {
                }
            }
        }
        return properties;
    }
}
