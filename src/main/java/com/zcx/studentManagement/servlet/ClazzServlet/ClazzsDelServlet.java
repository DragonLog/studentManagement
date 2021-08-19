package com.zcx.studentManagement.servlet.ClazzServlet;

import com.google.gson.*;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/delClazzs")
public class ClazzsDelServlet extends HttpServlet{  //批量删除班级
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String jsonClazzs = RequestUtil.getRequestBody(req);
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonElements = jsonParser.parse(jsonClazzs).getAsJsonArray(); //json串转换为对象数组
        List<Clazz> clazzs = new ArrayList<Clazz>();
        for (JsonElement clazz : jsonElements) {
            Clazz clazz1 = gson.fromJson(clazz, Clazz.class);//解析
            clazzs.add(clazz1);
        }
        int rows = ClazzDao.delClazzs(clazzs);
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
