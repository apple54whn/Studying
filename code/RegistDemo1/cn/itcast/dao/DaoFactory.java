package cn.itcast.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName:UserDaoFactory
 * @Description: 通过配置文件得到dao实现类名称并用反射创建类对象
 * @Author:Conanan
 **/
public class DaoFactory {
    private static Properties props = null;
    static {
        InputStream is = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
        props = new Properties();
        try {
            props.load(is);
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }

    }

    public static UserDao getUserDao() {
        try {
            Class c = Class.forName(props.getProperty("cn.itcast.dao.UserDao"));
            return (UserDao) c.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
