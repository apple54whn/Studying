package cn.itcast.service;

import cn.itcast.dao.DaoFactory;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.exception.MyException;

/**
 * @ClassName:UserService
 * @Description: TODO
 * @Author:Conanan
 * @Date:
 * @Version:1.0
 **/
public class UserService {
    private UserDao userdao = DaoFactory.getUserDao();

    public void regist(User user) throws  MyException {
        User _user = userdao.findByUsername(user.getUsername());
        if(_user!=null)
            throw new MyException("用户名"+user.getUsername()+"，已被注册");
        userdao.addUser(user);
    }

    public User login(User user) throws MyException {
        User _user =  userdao.findByUsername(user.getUsername());
        if(_user==null) {
            throw new MyException("用户名不存在");
        }
        else if(!user.getPassword().equals(_user.getPassword())){
            throw new MyException("密码错误");
        }
        return _user;


    }
}
