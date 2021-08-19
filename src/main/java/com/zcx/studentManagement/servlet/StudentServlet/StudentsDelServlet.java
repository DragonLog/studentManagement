package com.zcx.studentManagement.servlet.StudentServlet;

import com.google.gson.*;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/delStudents")
public class StudentsDelServlet extends HttpServlet {   //批量删除学生
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        req.setCharacterEncoding("utf-8");
        String jsonStudents = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonStudents).getAsJsonArray();
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < jsonElements.size(); i ++){
            JsonObject jsonObject = jsonElements.get(i).getAsJsonObject();
            String ID = jsonObject.get("id").getAsString();
            int id = Integer.parseInt(ID);
            String name = jsonObject.get("name").getAsString();
            String sex = jsonObject.get("sex").getAsString();
            String mobile = jsonObject.get("mobile").getAsString();
            String email = jsonObject.get("email").getAsString();
            String clazzName = jsonObject.get("clazzName").getAsString();
            String teacherName = jsonObject.get("teacherName").getAsString();
            java.util.Date date = null;
            try {
                date = simpleDateFormat.parse(jsonObject.get("birthday").getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date birthday = new Date(date.getTime());
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setSex(sex);
            student.setBirthday(birthday);
            student.setMobile(mobile);
            student.setEmail(email);
            student.setClazzName(clazzName);
            student.setTeacherName(teacherName);
            students.add(student);
        }
        int rows = StudentDao.delStudents(students);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        BaseResponse<Integer> response = new BaseResponse<Integer>();
        if(rows > 0){
            response.setCode(200);
            response.setMsg("删除成功");
        }else {
            response.setCode(600);
            response.setMsg("无数据删除");
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(response));
        out.flush();
        out.close();
    }
}
