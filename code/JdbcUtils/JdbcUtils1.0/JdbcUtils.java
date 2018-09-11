package cn.itacst;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description: JDBC工具
 * @version 1.0
 * @Author:Conanan
 **/

public class JdbcUtils {

    private static Properties props = null;
    /**
     * 只在JdbcUtils类加载时执行一次
     */
    static {
        //给props初始化，即加载dbconfig.properties配置文件到props对象中
        try {
            //通过classloader获取输入流；
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
            props = new Properties();
            props.load(is);
            Class.forName(props.getProperty("driverClassName"));//驱动也只需加载一次
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(props.getProperty("url"),
                    props.getProperty("username"), props.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

