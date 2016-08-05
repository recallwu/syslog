package main;

import bean.LogInfo;
import bean.SysInfo;
import db.OracleDB;
import log.LogHandle;
import utils.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Chaojier on 2016/7/28 16:59.
 */
public class LogSend {
    public static void main(String[] args) {

        while (true){
            String fromIdStr = ReadLocationProUtil.instance().getValue("read_location");
            Integer fromId = 0;
            if(StringUtil.ck_str(fromIdStr)){
                fromId = StringUtil.strToInteger(fromIdStr);
            }
            Integer endId = fromId;

            List<SysInfo> list = OracleDB.instance().getSysInfo(fromId);

            for (int i=0;i<list.size();i++) {
                LogInfo logInfo = LogHandle.instance().handleLog(list.get(i));
                LogSendUtil.instance().syslog(logInfo.getFacility(),logInfo.getSeverity(),logInfo.getMsg());
                if(i==list.size()-1){
                    ReadLocationProUtil.instance().setValue("read_location",list.get(i).getId().toString());
                    endId = list.get(i).getId();
                }
            }

            System.out.println("最近一次日志读取到的位置为：ID="+endId);
            System.out.println("最近一次的读取时间为："+ StringUtil.dateToStr(new Date()));
            System.out.println("读取数量为："+list.size());

            try {
                Thread.sleep(PropertiesValues.instance().getSpacing_interval());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        //LogSendUtil.instance().syslog(17,6,"test");
    }

}
