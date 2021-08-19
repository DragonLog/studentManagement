package com.zcx.studentManagement.servlet.StudentServlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zcx.studentManagement.dao.StudentDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Student;
import com.zcx.studentManagement.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/addStudent")
public class StudentAddServlet extends HttpServlet {    //添加学生
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //日期格式化
        req.setCharacterEncoding("utf-8");
        String jsonStudent = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(jsonStudent).getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String sex = jsonObject.get("sex").getAsString();
        String mobile = jsonObject.get("mobile").getAsString();
        String email = jsonObject.get("email").getAsString();
        String clazzName = jsonObject.get("clazzName").getAsString();
        String teacherName = jsonObject.get("teacherName").getAsString();
        java.util.Date date = null; //java日期类
        try {
            date = simpleDateFormat.parse(jsonObject.get("birthday").getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date birthday = new Date(date.getTime());
        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setBirthday(birthday);
        student.setMobile(mobile);
        student.setEmail(email);
        student.setClazzName(clazzName);
        student.setTeacherName(teacherName);
        int rows = StudentDao.addStudent(student);
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
