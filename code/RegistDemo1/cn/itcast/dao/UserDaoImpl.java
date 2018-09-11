package cn.itcast.dao;

import cn.itcast.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @ClassName:UserDao
 * @Description: TODO
 * @Author:Conanan
 * @Date:
 * @Version:1.0
 **/
public class UserDaoImpl implements UserDao {
    /**
     * 将数据保存在xml中，若没有此文件则先创建
     */
    private static String path = "F:\\users.xml";

    static {
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\n" +
                "<users> \n" +
                "</users>";
        File filePath = new File(path);
        try {
            if (!filePath.exists()) {
                filePath.createNewFile();
                FileOutputStream fos = new FileOutputStream(filePath);
                byte[] bytes = msg.getBytes();
                fos.write(bytes);
                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {

        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path);
            Element element = (Element) document.selectSingleNode("//user[@username='" + username + "']");
            if (element == null)
                return null;
            //封装到User
            User user = new User();
            user.setUsername(element.attributeValue("username"));
            user.setPassword(element.attributeValue("password"));
            return user;
        } catch (DocumentException e) {
            throw new RuntimeException();
        }
    }

    public void addUser(User user) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(path);
            Element root = document.getRootElement();
            Element us = root.addElement("user");
            us.addAttribute("username", user.getUsername());
            us.addAttribute("password", user.getPassword());

            OutputFormat format = OutputFormat.createPrettyPrint();
//            format.setTrimText(true);
            try {
                XMLWriter xmlWriter = new XMLWriter(
                        new OutputStreamWriter(new FileOutputStream(path),
                                "utf-8"), format);
                xmlWriter.write(document);
                xmlWriter.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
