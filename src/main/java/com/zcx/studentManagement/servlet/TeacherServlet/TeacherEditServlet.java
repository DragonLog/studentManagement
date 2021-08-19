package com.zcx.studentManagement.servlet.TeacherServlet;

import com.google.gson.Gson;
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

@WebServlet(urlPatterns = "/editTeacher")
public class TeacherEditServlet extends HttpServlet {   //更新老师
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String jsonTeacher = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        Teacher TeacherBody = gson.fromJson(jsonTeacher, Teacher.class);
        Teacher teacher = TeacherDao.getTeacherById(TeacherBody.getId());
        if(teacher != null){
            teacher.setId(TeacherBody.getId());
            teacher.setName(TeacherBody.getName());
            teacher.setSex(TeacherBody.getSex());
            teacher.setMobile(TeacherBody.getMobile());
            teacher.setEmail(TeacherBody.getEmail());
        }
        int rows = TeacherDao.updateTeacher(teacher);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        BaseResponse<Integer> response = new BaseResponse<Integer>();
        if(rows > 0){
            response.setCode(200);
            response.setMsg("编辑成功");

        }else{
            response.setCode(600);
            response.setMsg("编辑失败");
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(response));
        out.flush();
        out.close();
    }
}
