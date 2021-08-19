package com.zcx.studentManagement.servlet.StudentServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.StringUtils;
import com.zcx.studentManagement.dao.StudentDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/StudentAll")
public class StudentAllServlet extends HttpServlet {    //获取学生分页
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        List<Student> students = new ArrayList<Student>();
        students = StudentDao.getStudents(StringUtils.isNullOrEmpty(page) ? 1 : Integer.parseInt(page), StringUtils.isNullOrEmpty(limit) ? 10 : Integer.parseInt(limit));
        BaseResponse<List<Student>> response = new BaseResponse<List<Student>>();
        response.setCode(200);
        response.setMsg("查询得到的信息");
        response.setData(students);
        response.setCount(StudentDao.getCount());
        Gson  gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();    //gson设置时间格式
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        String json = gson.toJson(response);
        PrintWriter out = resp.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
