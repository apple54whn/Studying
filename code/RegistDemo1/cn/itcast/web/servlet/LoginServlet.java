package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.exception.MyException;
import cn.itcast.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //依赖UserService
        UserService userService = new UserService();

        //封装表单数据到bean
        User user = new User();
        try {
            BeanUtils.populate(user,request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            User _user = userService.login(user);
            request.getSession().setAttribute("succUser",_user);
            response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
        } catch (MyException e){
            request.setAttribute("error",e.getMessage());
            request.setAttribute("user",user);
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }

    }

}
