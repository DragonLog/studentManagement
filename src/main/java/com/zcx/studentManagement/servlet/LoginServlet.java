package com.zcx.studentManagement.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zcx.studentManagement.dao.AdminDao;
import com.zcx.studentManagement.entity.Administrator;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/login") //使用该注解后就不需要在web.xml文件中声明servlet了
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  //登录验证
        req.setCharacterEncoding("utf-8");  //设置请求编码
        String jsonLogin = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(jsonLogin).getAsJsonObject();    //将请求内容转换为json对象
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String vscode = jsonObject.get("vscode").getAsString().toUpperCase();   //验证码不区分大小写
        String TrueVsCode = ((String)req.getSession().getAttribute("validateCode")).toUpperCase();  //真正可以使用的验证码要从session中取
        resp.setContentType("application/json");    //设置响应类型
        BaseResponse<Integer> baseResponse = new BaseResponse<Integer>();
        if(vscode.equals(TrueVsCode)){//验证码正确
            List<Administrator> administrators = AdminDao.getAdministratorByName(username);
            if(administrators.size() > 0){//用户名正确
                for (int i = 0; i < administrators.size(); i ++){
                    if (password.equals(administrators.get(i).getPassword())){
                        baseResponse.setCode(200);  //成功返回码
                        baseResponse.setMsg("登录成功");
                        HttpSession session = req.getSession();
                        session.setAttribute("username", username);
                        session.setAttribute("password", password);
                        break;
                    }
                    if (i == administrators.size() - 1){
                        baseResponse.setCode(500);  //错误返回码
                        baseResponse.setMsg("密码错误");
                    }
                }
            }else{
                baseResponse.setCode(400);  //错误返回码
                baseResponse.setMsg("用户名不存在");
            }
        }else {//验证码错误
            baseResponse.setCode(300);  //错误返回码
            baseResponse.setMsg("验证码错误");
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(baseResponse));   //以json形式返回
        out.flush();
        out.close();
    }
}
