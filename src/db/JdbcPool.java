package db;

import utils.PropertiesValues;
import utils.StringUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Created by Chaojier on 2016/8/4 16:03.
 */
public class JdbcPool implements DataSource {

    private JdbcPool(){

    }

    private static JdbcPool instance = new JdbcPool();

    public static JdbcPool instance(){
        return instance;
    }

    /**
     * @Field: listConnections
     *  使用LinkedList集合来存放数据库链接，
     *  由于要频繁读写List集合，所以这里使用LinkedList存储数据库连接比较合适
     */
    private static LinkedList<Connection> listConnections = new LinkedList<Connection>();

    static{
        //DB信息
        String username = PropertiesValues.instance().getDb_username();
        String password = PropertiesValues.instance().getDb_password();
        String url = getUrl();

        //数据库连接池的初始化连接数大小
        Integer jdbcPoolInitSize = PropertiesValues.instance().getPool_init_size();

        try {
            //加载数据库驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                //System.out.println("获取到了链接" + conn);
                //将获取到的数据库连接加入到listConnections集合中，listConnections集合此时就是一个存放了数据库连接的连接池
                listConnections.add(conn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        //如果数据库连接池中的连接对象的个数大于0
        if (listConnections.size()>0) {
            //从listConnections集合中获取一个数据库连接
            final Connection conn = listConnections.removeFirst();
            //System.out.println("listConnections数据库连接池大小是" + listConnections.size());
            //返回Connection对象的代理对象
            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler(){
                @Override
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    if(!method.getName().equals("close")){
                        return method.invoke(conn, args);
                    }else{
                        //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
                        listConnections.add(conn);
                        //System.out.println(conn + "被还给listConnections数据库连接池了！！");
                        //System.out.println("listConnections数据库连接池大小为" + listConnections.size());
                        return null;
                    }
                }
            });
        }else {
            throw new RuntimeException("对不起，数据库忙");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    private static String getUrl() {
        StringBuilder url = new StringBuilder();

        //从配置文件中取得相关DB信息
        String host = PropertiesValues.instance().getDb_host();
        String port = PropertiesValues.instance().getDb_port();
        String sid = PropertiesValues.instance().getDb_sid();

        if (StringUtil.ck_str(host) && StringUtil.ck_str(port) && StringUtil.ck_str(sid)) {
            //格式：jdbc:oracle:thin:@10.10.10.10:1521:ora9i
            url.append("jdbc:oracle:thin:@");
            url.append(host);
            url.append(":");
            url.append(port);
            url.append(":");
            url.append(sid);
        }
        return url.toString();
    }
}
