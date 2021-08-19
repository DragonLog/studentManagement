package com.zcx.studentManagement.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{ //登录验证过滤器

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        if(path.contains("login.jsp") || path.contains("/login") || path.contains("/DrawValidateCode") || path.contains("/layui") || path.contains("/image")){
            chain.doFilter(req, resp);  //登录页面，登录请求，验证码请求，前端资源和图片一律放行
        }else{
            String username = (String)session.getAttribute("username");
            if (username == null){ //用户不存在一律重定向到登录页
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
