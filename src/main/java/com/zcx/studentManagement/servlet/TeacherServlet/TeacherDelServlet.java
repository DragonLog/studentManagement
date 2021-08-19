package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
import com.zcx.studentManagement.dao.TeacherDao;
import com.zcx.studentManagement.entity.BaseResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delTeacher")
public class TeacherDelServlet extends HttpServlet {    //删除教师

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ID = req.getParameter("id");
        int id = Integer.parseInt(ID);
        int rows = TeacherDao.delTeacherById(id);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        BaseResponse<Integer> response = new BaseResponse<Integer>();
        if (rows > 0){
            response.setCode(200);
            response.setMsg("删除成功");
        } else {
            response.setCode(600);
            response.setMsg("删除失败");
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        PrintWriter out = resp.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
