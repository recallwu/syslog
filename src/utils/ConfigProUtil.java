package utils;

/**
 * Created by Chaojier on 2016/8/2 10:27.
 */
public class ConfigProUtil extends PropertiesUtil {
    private static final String SERVER_PATH = "conf/config.properties";

    private static ConfigProUtil instance = new ConfigProUtil();

    private ConfigProUtil() {
        super.setPath(SERVER_PATH);
    }

    public static ConfigProUtil instance() {
        return instance;
    }

}
