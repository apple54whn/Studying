package cn.itcast.web.servlet;

import cn.itcast.vcode.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "VerifyCodeServlet",urlPatterns = "/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bi = verifyCode.getImage();
        request.getSession().setAttribute("vcode",verifyCode.getText());
        VerifyCode.output(bi,response.getOutputStream());
    }
}
