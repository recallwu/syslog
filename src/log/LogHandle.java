package log;

import bean.LogInfo;
import bean.SysInfo;
import utils.DateUtil;
import utils.PropertiesValues;
import utils.StringUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chaojier on 2016/8/2 11:13.
 */
public class LogHandle {

    private LogHandle(){};

    private static LogHandle instance = new LogHandle();

    public static LogHandle instance(){
        return instance;
    }

    public LogInfo handleLog(SysInfo sysinfo){
        String logTime = timeHandle(sysinfo.getTime());
        String subUser = sysinfo.getOperator();
        String ip = PropertiesValues.instance().getUsm_host();
        String sip = sysinfo.getIssueOn();
        String opType = handleOperateType(sysinfo.getAct());
        Integer opResult =handleOperateResult(sysinfo.getStatus(),sysinfo.getAct());
        String opText = handleDeails(sysinfo.getDetails());

        StringBuilder msg = new StringBuilder();
        msg.append("LogTime=\"");
        msg.append(logTime);
        msg.append("\";SubUser=\"");
        msg.append(subUser);
        msg.append("\";IP=\"");
        msg.append(ip);
        msg.append("\";Sip=\"");
        msg.append(sip);
        msg.append("\";OpType=\"");
        msg.append(opType);
        msg.append("\";OpResult=\"");
        msg.append(opResult);
        msg.append("\";OpText=\"");
        msg.append(opText);
        msg.append("\";");

        Integer facilityValue = getFacilityValue(sysinfo.getApp());
        Integer severityValue = getSeverityValue(sysinfo.getAct(),sysinfo.getStatus());

        return new LogInfo(facilityValue,severityValue,msg.toString());
    }

    private String handleDeails(String details) {
        return details.replaceAll("\"","");
    }

    private Integer getFacilityValue(Integer app) {
        return PropertiesValues.instance().getModuleValueMap().get(app);
    }

    private Integer getSeverityValue(String act, Integer status) {
        if(act.trim().equals("Authentication failed")){
            return 4;
        }else{
            if(status.equals(1)){
                return 5;
            }
        }
        return 6;
    }

    private String timeHandle(String time){
        Date date = StringUtil.strToDate(time);
        date = DateUtil.addTime(date, Calendar.HOUR_OF_DAY,8);
        return StringUtil.dateToStr(date);
    }

    private String handleOperateType(String opType){
        if(StringUtil.ck_str(opType)){
            if(opType.trim().equals("Session open")||opType.trim().equals("Authentication failed")){
                return "Login";
            }else if(opType.trim().equals("Session closed")){
                return "Logout";
            }
        }
        return opType;
    }

    private Integer handleOperateResult(Integer opResult, String opType) {
        if (opResult != null && StringUtil.ck_str(opType)) {
            if (!opType.trim().equals("Authentication failed") && opResult == 2) {
                return 1;
            }
        }
        return 0;
    }
}
