package bean;

/**
 * Created by Chaojier on 2016/7/29 10:52.
 */
public class SysInfo {
    private Integer id;
    private Integer app;//operate type
    private String operator;//username
    private String act;//action
    private String details;
    private String exec_type;
    private String involved_object;//user computer name
    private String actionOn;
    private String issueOn;//ip
    private Integer status;//success(2) or fail(1)
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getExec_type() {
        return exec_type;
    }

    public void setExec_type(String exec_type) {
        this.exec_type = exec_type;
    }

    public String getInvolved_object() {
        return involved_object;
    }

    public void setInvolved_object(String involved_object) {
        this.involved_object = involved_object;
    }

    public String getActionOn() {
        return actionOn;
    }

    public void setActionOn(String actionOn) {
        this.actionOn = actionOn;
    }

    public String getIssueOn() {
        return issueOn;
    }

    public void setIssueOn(String issueOn) {
        this.issueOn = issueOn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer type) {
        this.status = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
