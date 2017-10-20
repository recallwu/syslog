package utils;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Chaojier on 2016/8/1 14:22.
 */
public class LogSendUtil {

    /*SyslogIF syslog = null;

    private LogSendUtil() {
        String syslog_host = PropertiesValues.instance().getSyslog_host();
        if (syslog_host != null && !syslog_host.trim().equals("")) {
            //获取syslog的操作类，使用udp协议。syslog支持"udp", "tcp", "unix_syslog", "unix_socket"协议
            syslog = Syslog.getInstance("udp");
            //设置syslog服务器端地址
            syslog.getConfig().setHost(syslog_host);
            //设置syslog接收端口，默认514
            syslog.getConfig().setPort(514);
        }
    }

    private static LogSendUtil instance = new LogSendUtil();

    public static LogSendUtil instance() {
        return instance;
    }

    public void sendSyslog(int value,String message){
        if(syslog!=null) {
            syslog.log(value,message);
        }
    }*/

    private static LogSendUtil instance = new LogSendUtil();

    public static LogSendUtil instance() {
        return instance;
    }

    private DatagramSocket socket;

    private SimpleDateFormat date1Format;
    private SimpleDateFormat date2Format;

    private boolean includeDate;

    private LogSendUtil() {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        this.includeDate = true;
        if (this.includeDate) {
            this.date1Format = new SimpleDateFormat("MMM  d HH:mm:ss ", Locale.US);
            this.date2Format = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.US);
            this.date1Format.setTimeZone(TimeZone.getDefault());
            this.date2Format.setTimeZone(TimeZone.getDefault());
        }
    }

    public void syslog(int fac, int pri, String msg) {
        String host = PropertiesValues.instance().getSyslog_host();
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Integer port = PropertiesValues.instance().getSyslog_port();
        if (addr != null && port != null && !(port == 0)) {
            this.syslog(addr, port, fac, pri, msg);
        }
    }

    public void syslog(InetAddress addr, int port, int fac, int pri, String msg) {
        int pricode;
        int length;
        int idx;
        byte[] data;
        byte[] sBytes;
        // String nmObj;
        String strObj;

        pricode = computeCode(fac, pri);
        Integer priObj = new Integer(pricode);

        // nmObj = new String(Thread.currentThread().getName());

        length = 4 /*+ nmObj.getBytes().length*/ + msg.getBytes().length;
        // length += (pricode > 99) ? 3 : ((pricode > 9) ? 2 : 1);

        // String dStr = null;
        // if (this.includeDate) {
        //     // See note above on why we have two formats...
        //     Calendar now = Calendar.getInstance();
        //     if (now.get(Calendar.DAY_OF_MONTH) < 10)
        //         dStr = this.date1Format.format(now.getTime());
        //     else
        //         dStr = this.date2Format.format(now.getTime());
        //
        //     length += dStr.getBytes().length;
        // }

        data = new byte[length];

        idx = 0;
        data[idx++] = '<';

        strObj = priObj.toString(priObj.intValue());
        sBytes = strObj.getBytes();
        System.arraycopy(sBytes, 0, data, idx, sBytes.length);
        idx += sBytes.length;

        data[idx++] = '>';

        // if (this.includeDate) {
        //     sBytes = dStr.getBytes();
        //     System.arraycopy(sBytes, 0, data, idx, sBytes.length);
        //     idx += sBytes.length;
        // }

        // sBytes = nmObj.getBytes();
        // System.arraycopy(sBytes, 0, data, idx, sBytes.length);
        // idx += sBytes.length;
        //
        // data[idx++] = ':';
        // data[idx++] = ' ';

        sBytes = msg.getBytes();
        System.arraycopy(sBytes, 0, data, idx, sBytes.length);
        // idx += sBytes.length;

        // data[idx] = 0;
        String dataStr = new String(data);
        System.out.println(dataStr);
        DatagramPacket packet = new DatagramPacket(data, length, addr, port);

        try {
            socket.send(packet);
        } catch (IOException ex) {
            String message = "error sending message: '" + ex.getMessage() + "'";

            System.err.println(message);
        }

    }

    private int computeCode(int facility, int priority) {
        return ((facility << 3) | priority);
    }

}
