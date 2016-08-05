package db;

import bean.SysInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaojier on 2016/8/1 9:53.
 */
public class OracleDB {
    private OracleDB() {
    }

    private static OracleDB instance = new OracleDB();

    public static OracleDB instance() {
        return instance;
    }
//    private String username = PropertiesValues.instance().getDb_username();
//    private String password = PropertiesValues.instance().getDb_password();

//    public List<SysInfo> getSysInfo(Integer fromId) {
//        Connection conn = null;
//        PreparedStatement  stat = null;
//        ResultSet rs = null;
//
//        List<SysInfo> list = new ArrayList<SysInfo>();
//
//        String url = getUrl();
//
//        if (StringUtil.ck_str(username) && StringUtil.ck_str(password) && StringUtil.ck_str(url)) {
//            try {
//                Class.forName("oracle.jdbc.driver.OracleDriver");
//                conn = DriverManager.getConnection(url, username, password);
//                String sql = " SELECT                                   "
//                        + " 	clog. ID,                              "
//                        + " 	clog.app,                              "
//                        + " 	clog_operators. OPERATOR,              "
//                        + " 	clog_act.act,                          "
//                        + " 	clog.details,                          "
//                        + " 	clog.exec_type,                        "
//                        + " 	clog.involved_object,                  "
//                        + " 	host.server actionOn,                  "
//                        + " 	client.server issueOn,                 "
//                        + " 	clog.status,                            "
//                        + "   clog.utc_time "
//                        + " FROM                                     "
//                        + " 	clog,                                  "
//                        + " 	clog_act,                              "
//                        + " 	clog_operators,                        "
//                        + " 	clog_servers host,                     "
//                        + " 	clog_servers client                    "
//                        + " WHERE                                    "
//                        //+ " 	clog.app IN (6, 17)                    "
//                        + " clog.act = clog_act. ID              "
//                        + " AND clog.app = clog_act.app              "
//                        + " AND clog. OPERATOR = clog_operators. ID  "
//                        + " AND host. ID = clog.host                 "
//                        + " AND client. ID = clog.client_host        "
//                        + " AND clog. ID>?"
//                        + " ORDER BY clog. ID";
//                stat = conn.prepareStatement(sql);
//                stat.setInt(1,fromId);
//                rs = stat.executeQuery();
//                while (rs.next()) {
//                    SysInfo info = new SysInfo();
//                    info.setId(rs.getInt("ID"));
//                    info.setApp(rs.getInt("app"));
//                    info.setOperator(rs.getString("OPERATOR"));
//                    info.setAct(rs.getString("act"));
//                    info.setDetails(rs.getString("details"));
//                    info.setExec_type(rs.getString("exec_type"));
//                    info.setInvolved_object(rs.getString("involved_object"));
//                    info.setActionOn(rs.getString("actionOn"));
//                    info.setIssueOn(rs.getString("issueOn"));
//                    info.setStatus(rs.getInt("status"));
//                    info.setTime(rs.getString("utc_time"));
//                    list.add(info);
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                instance.closeDb(conn,stat,rs);
//            }
//        }
//
//        return list;
//    }

//    private String getUrl() {
//        StringBuilder url = new StringBuilder();
//
//        //从配置文件中取得相关DB信息
//        String host = PropertiesValues.instance().getDb_host();
//        String port = PropertiesValues.instance().getDb_port();
//        String sid = PropertiesValues.instance().getDb_sid();
//
//        if (StringUtil.ck_str(host) && StringUtil.ck_str(port) && StringUtil.ck_str(sid)) {
//            //格式：jdbc:oracle:thin:@10.10.10.10:1521:ora9i
//            url.append("jdbc:oracle:thin:@");
//            url.append(host);
//            url.append(":");
//            url.append(port);
//            url.append(":");
//            url.append(sid);
//        }
//        return url.toString();
//    }
//
//    private void closeDb(Connection conn, Statement stat, ResultSet rs) {
//        try {
//            if (rs != null)
//                rs.close();
//            if (stat != null)
//                stat.close();
//            if (conn != null)
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<SysInfo> getSysInfo(Integer fromId) {
        Connection conn = getConnection();
        PreparedStatement  stat = null;
        ResultSet rs = null;

        List<SysInfo> list = new ArrayList<SysInfo>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String sql = " SELECT                                   "
                    + " 	clog. ID,                              "
                    + " 	clog.app,                              "
                    + " 	clog_operators. OPERATOR,              "
                    + " 	clog_act.act,                          "
                    + " 	clog.details,                          "
                    + " 	clog.exec_type,                        "
                    + " 	clog.involved_object,                  "
                    + " 	host.server actionOn,                  "
                    + " 	client.server issueOn,                 "
                    + " 	clog.status,                            "
                    + "   clog.utc_time "
                    + " FROM                                     "
                    + " 	clog,                                  "
                    + " 	clog_act,                              "
                    + " 	clog_operators,                        "
                    + " 	clog_servers host,                     "
                    + " 	clog_servers client                    "
                    + " WHERE                                    "
                    //+ " 	clog.app IN (6, 17)                    "
                    + " clog.act = clog_act. ID              "
                    + " AND clog.app = clog_act.app              "
                    + " AND clog. OPERATOR = clog_operators. ID  "
                    + " AND host. ID = clog.host                 "
                    + " AND client. ID = clog.client_host        "
                    + " AND clog. ID>?"
                    + " ORDER BY clog. ID";
            stat = conn.prepareStatement(sql);
            stat.setInt(1,fromId);
            rs = stat.executeQuery();
            while (rs.next()) {
                SysInfo info = new SysInfo();
                info.setId(rs.getInt("ID"));
                info.setApp(rs.getInt("app"));
                info.setOperator(rs.getString("OPERATOR"));
                info.setAct(rs.getString("act"));
                info.setDetails(rs.getString("details"));
                info.setExec_type(rs.getString("exec_type"));
                info.setInvolved_object(rs.getString("involved_object"));
                info.setActionOn(rs.getString("actionOn"));
                info.setIssueOn(rs.getString("issueOn"));
                info.setStatus(rs.getInt("status"));
                info.setTime(rs.getString("utc_time"));
                list.add(info);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            instance.release(conn,stat,rs);
        }

        return list;
    }

    private Connection getConnection(){
        Connection conn = null;
        try {
            conn =  JdbcPool.instance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void release(Connection conn, Statement stat, ResultSet rs){
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
