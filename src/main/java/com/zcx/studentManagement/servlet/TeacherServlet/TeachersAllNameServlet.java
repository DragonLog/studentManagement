package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
import com.zcx.studentManagement.dao.TeacherDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/TeachersAllName")
public class TeachersAllNameServlet extends HttpServlet {   //获取全体教师姓名
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers = TeacherDao.getAllTeachers();
        BaseResponse<List<Teacher>> response = new BaseResponse<List<Teacher>>();
        response.setCode(200);
        response.setMsg("查询得到的信息");
        response.setData(teachers);
        response.setCount(TeacherDao.getCount());
        Gson gson = new Gson();
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        String json = gson.toJson(response);
        PrintWriter out = resp.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
