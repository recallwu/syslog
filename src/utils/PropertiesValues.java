package utils;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chaojier on 2016/8/2 16:25.
 */
public class PropertiesValues {

    private Integer read_location = 0;
    private Integer spacing_interval = 0;

    private static final String SYSLOG_DEFAULT_PROT = "514";

    private String syslog_host = "";
    private Integer syslog_port = 0;
    private String usm_host = "";
    private String db_host = "";
    private String db_port = "";
    private String db_sid = "";
    private String db_username = "";
    private String db_password = "";

    private Integer pool_init_size = 0;

    private Map<String,Integer> localValueMap = new HashMap<String,Integer>();

    private Map<Integer,Integer> moduleValueMap = new HashMap<Integer,Integer>();

    private PropertiesValues() {
        init();
    }

    private static PropertiesValues instance = new PropertiesValues();

    public static PropertiesValues instance() {
        return instance;
    }

    private void init() {

        setLocalValue();
        initFacilityModule();
        initSendLog();
        initServer();
    }

    private void initFacilityModule() {
        String local0Str = FacilityModuleProUtil.instance().getValue("local0");
        String local1Str = FacilityModuleProUtil.instance().getValue("local1");
        String local2Str = FacilityModuleProUtil.instance().getValue("local2");
        String local3Str = FacilityModuleProUtil.instance().getValue("local3");
        String local4Str = FacilityModuleProUtil.instance().getValue("local4");
        Integer local0 = StringUtil.strToInteger(local0Str);
        Integer local1 = StringUtil.strToInteger(local1Str);
        Integer local2 = StringUtil.strToInteger(local2Str);
        Integer local3 = StringUtil.strToInteger(local3Str);
        Integer local4 = StringUtil.strToInteger(local4Str);
        moduleValueMap.put(local0,localValueMap.get("local0"));
        moduleValueMap.put(local1,localValueMap.get("local1"));
        moduleValueMap.put(local2,localValueMap.get("local2"));
        moduleValueMap.put(local3,localValueMap.get("local3"));
        moduleValueMap.put(local4,localValueMap.get("local4"));
    }

    private void initSendLog() {
        String read_locationStr = ReadLocationProUtil.instance().getValue("read_location");
        read_location = StringUtil.strToInteger(read_locationStr);
    }

    private void initServer() {
        syslog_host = ConfigProUtil.instance().getValue("syslog_host");
        String syslog_portStr = ConfigProUtil.instance().getValue("syslog_port");
        if(!StringUtil.ck_str(syslog_portStr)){
            syslog_portStr = SYSLOG_DEFAULT_PROT;
        }
        syslog_port = StringUtil.strToInteger(syslog_portStr);
        usm_host = ConfigProUtil.instance().getValue("usm_host");
        db_host = ConfigProUtil.instance().getValue("db_host");
        db_port = ConfigProUtil.instance().getValue("db_port");
        db_sid = ConfigProUtil.instance().getValue("db_sid");
        db_username = ConfigProUtil.instance().getValue("db_username");
        db_password = ConfigProUtil.instance().getValue("db_password");
        String spacing_intervalStr = ConfigProUtil.instance().getValue("spacing_interval");
        spacing_interval = StringUtil.strToInteger(spacing_intervalStr);
        String pool_init_sizeStr = ConfigProUtil.instance().getValue("pool_init_size");
        pool_init_size = StringUtil.strToInteger(pool_init_sizeStr);
    }

    private void setLocalValue(){
        localValueMap.put("local0",16);
        localValueMap.put("local1",17);
        localValueMap.put("local2",18);
        localValueMap.put("local3",19);
        localValueMap.put("local4",20);
        localValueMap.put("local5",21);
        localValueMap.put("local6",22);
        localValueMap.put("local7",23);
    }

    public Integer getRead_location() {
        return read_location;
    }

    public Integer getSpacing_interval() {
        return spacing_interval;
    }

    public String getSyslog_host() {
        return syslog_host;
    }

    public String getUsm_host() {
        return usm_host;
    }

    public String getDb_host() {
        return db_host;
    }

    public String getDb_port() {
        return db_port;
    }

    public String getDb_sid() {
        return db_sid;
    }

    public String getDb_username() {
        return db_username;
    }

    public String getDb_password() {
        return db_password;
    }

    public Map<Integer, Integer> getModuleValueMap() {
        return moduleValueMap;
    }

    public Integer getSyslog_port() {
        return syslog_port;
    }

    public Integer getPool_init_size() {
        return pool_init_size;
    }
}
