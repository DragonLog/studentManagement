package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
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

@WebServlet(urlPatterns = "/TeacherAll")
public class TeacherAllServlet extends HttpServlet {    //获取全体教师
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        List<Teacher> teachers = new ArrayList<Teacher>();
        teachers = TeacherDao.getTeachers(StringUtils.isNullOrEmpty(page) ? 1 : Integer.parseInt(page), StringUtils.isNullOrEmpty(limit) ? 10 : Integer.parseInt(limit));
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
