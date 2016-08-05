package bean;

/**
 * Created by Chaojier on 2016/8/2 13:37.
 */
public class LogInfo {

    private Integer facility;
    private Integer severity;
    private String msg;

    public LogInfo(Integer facility,Integer severity, String msg) {
        this.facility = facility;
        this.severity = severity;
        this.msg = msg;
    }

    public Integer getFacility() {
        return facility;
    }

    public Integer getSeverity() {
        return severity;
    }

    public String getMsg() {
        return msg;
    }

}
