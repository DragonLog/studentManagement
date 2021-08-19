package com.zcx.studentManagement.servlet.ClazzServlet;

import com.google.gson.Gson;
import com.zcx.studentManagement.dao.ClazzDao;
import com.zcx.studentManagement.entity.BaseResponse;
import com.zcx.studentManagement.entity.Clazz;
import com.zcx.studentManagement.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/editClazz")
public class ClazzEditServlet extends HttpServlet{  //更新班级
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String jsonClazz = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        Clazz ClazzBody = gson.fromJson(jsonClazz, Clazz.class);    //把json串转换为对象
        Clazz clazz = ClazzDao.getClazzById(ClazzBody.getId());
        if(clazz != null){
            clazz.setId(ClazzBody.getId());
            clazz.setName(ClazzBody.getName());
            clazz.setInformation(ClazzBody.getInformation());
        }
        int rows = ClazzDao.updateClazz(clazz);
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
