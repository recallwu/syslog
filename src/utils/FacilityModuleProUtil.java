package utils;

/**
 * Created by Chaojier on 2016/8/2 10:27.
 */
public class FacilityModuleProUtil extends PropertiesUtil {
    private static final String FACILITY_MODULE_PATH = "conf/facility_module.properties";

    private static FacilityModuleProUtil instance = new FacilityModuleProUtil();

    private FacilityModuleProUtil() {
        super.setPath(FACILITY_MODULE_PATH);
    }

    public static FacilityModuleProUtil instance() {
        return instance;
    }

}
