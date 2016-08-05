package utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by Chaojier on 2016/7/29 16:21.
 */
public class PropertiesUtil {
    private String path = "";

    public String getValue(String key) {
        if (path != null && !path.trim().equals("")) {
            Properties prop = new Properties();
            try {
                InputStream in = new BufferedInputStream(new FileInputStream(path));
                prop.load(in);
                in.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            return prop.getProperty(key);
        }
        return "";
    }


    public void setPath(String path) {
        this.path = path;
    }
}
