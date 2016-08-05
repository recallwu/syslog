package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Chaojier on 2016/8/2 10:27.
 */
public class ReadLocationProUtil extends PropertiesUtil {
    private static final String SEND_LOG_PATH = "conf/read_location.properties";

    private static ReadLocationProUtil instance = new ReadLocationProUtil();

    private ReadLocationProUtil() {
        super.setPath(SEND_LOG_PATH);
    }

    public static ReadLocationProUtil instance() {
        return instance;
    }


    public void setValue(String key, String value) {
        if (SEND_LOG_PATH != null && !SEND_LOG_PATH.trim().equals("")) {
            Properties prop = new Properties();
            try {
                FileOutputStream oFile = new FileOutputStream(SEND_LOG_PATH, false);
                prop.setProperty(key, value);
                prop.store(oFile, "");
                oFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
