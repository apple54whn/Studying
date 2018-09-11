package cn.itcast.dao;

import cn.itcast.domain.User;

/**
 * @ClassName:UserDao
 * @Description: TODO
 * @Author:Conanan
 **/
public interface UserDao {

    User findByUsername(String username);

    void addUser(User user);
}
