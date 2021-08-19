package com.zcx.studentManagement.servlet.ClazzServlet;

import com.google.gson.Gson;
import com.zcx.studentManagement.dao.ClazzDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Clazz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ClazzsAllName") //获取全部班级名称
public class ClazzAllNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Clazz> clazzs = new ArrayList<Clazz>();
        clazzs = ClazzDao.getAllClazzs();
        BaseResponse<List<Clazz>> response = new BaseResponse<List<Clazz>>();
        response.setCode(200);
        response.setMsg("查询得到的信息");
        response.setData(clazzs);
        response.setCount(ClazzDao.getCount());
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
