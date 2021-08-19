package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zcx.studentManagement.dao.TeacherDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Teacher;
import com.zcx.studentManagement.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/addTeacher")
public class TeacherAddServlet extends HttpServlet {    //添加老师
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String jsonTeacher = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(jsonTeacher).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String sex = jsonObject.get("sex").getAsString();
        String mobile = jsonObject.get("mobile").getAsString();
        String email = jsonObject.get("email").getAsString();
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSex(sex);
        teacher.setMobile(mobile);
        teacher.setEmail(email);
        int rows = TeacherDao.addTeacher(teacher);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        BaseResponse<Integer> response = new BaseResponse<Integer>();
        if(rows > 0){
            response.setCode(200);
            response.setMsg("添加成功");
        }else{
            response.setCode(600);
            response.setMsg("添加失败");
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(response));
        out.flush();
        out.close();
    }
}
