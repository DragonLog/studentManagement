package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/delTeachers")
public class TeachersDelServlet extends HttpServlet {   //批量删除教师
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String jsonTeachers = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonTeachers).getAsJsonArray();
        List<Teacher> teachers = new ArrayList<Teacher>();
        for (JsonElement teacher : jsonElements) {
            Teacher teacher1 = gson.fromJson(teacher, Teacher.class);//解析
            teachers.add(teacher1);
        }
        int rows = TeacherDao.delTeachers(teachers);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        BaseResponse<Integer> response = new BaseResponse<Integer>();
        if(rows > 0){
            response.setCode(200);
            response.setMsg("删除成功");
            response.setData(0);
            response.setCount(0);
        }else {
            response.setCode(600);
            response.setMsg("无数据删除");
            response.setData(0);
            response.setCount(0);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(response));
        out.flush();
        out.close();
    }
}
