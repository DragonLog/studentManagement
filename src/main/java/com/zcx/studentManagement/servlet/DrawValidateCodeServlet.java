package com.zcx.studentManagement.servlet;

import com.zcx.studentManagement.utils.ValidateCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/DrawValidateCode")
public class DrawValidateCodeServlet extends HttpServlet{   //返回验证码图片

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("img/jpg"); //返回类型为jpg图片
        int width = 300;
        int height = 100;
        ValidateCodeUtil validateCodeUtil = ValidateCodeUtil.getInstance();
        validateCodeUtil.set(width, height);
        String validateCode = validateCodeUtil.generateCheckCode();
        req.getSession().setAttribute("validateCode", validateCode);    //把生成好的验证码放入session
        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(validateCodeUtil.generateCheckImg(validateCode), "jpg", outputStream);
    }
}
