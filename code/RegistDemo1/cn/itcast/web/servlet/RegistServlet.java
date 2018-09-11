package cn.itcast.web.servlet;

import cn.itcast.domain.User;
import cn.itcast.exception.MyException;
import cn.itcast.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@javax.servlet.annotation.WebServlet(name = "RegistServlet", urlPatterns = "/RegistServlet")
public class RegistServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //封装表单到bean
        UserService userService = new UserService();
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //校验、所有错误信息
        Map<String, String> map = new HashMap<>();
        //用户名校验
        String username = user.getUsername();
        if (username == null || username.trim().isEmpty())
            map.put("username", " 用户名不能为空");
        else if (username.length() < 3 || username.length() > 15)
            map.put("username", " 用户名长度必须在3~16之间");
        //密码校验
        String password = user.getPassword();
        if (password == null || password.trim().isEmpty())
            map.put("password", " 密码不能为空");
        else if (password.length() < 3 || password.length() > 15)
            map.put("password", " 密码长度必须在8~16之间");
        //验证码校验
        String verifyCode = user.getVerifyCode();
        HttpSession session = request.getSession();
        if (verifyCode == null || verifyCode.trim().isEmpty())
            map.put("verifyCode", " 验证码不能为空");
        else if (!verifyCode.equalsIgnoreCase((String) session.getAttribute("vcode")))
            map.put("verifyCode", " 验证码错误");

        //判断map
        if (map != null && map.size() > 0) {
            request.setAttribute("user", user);
            request.setAttribute("map", map);
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
            return;
        }

        try {
            userService.regist(user);

            response.getWriter().write("注册成功，<a href='" + request.getContextPath() + "/user/login.jsp'>点击这里进行登录</a>");

        } catch (MyException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("user", user);
            request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
        }
    }

}
