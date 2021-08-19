<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台管理</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body class="layui-layout-body" style="background-image: url(image/bg.jpg); background-size: cover;">
    <div class="layui-layout layui-layout-admin" style="opacity: 0.80;">
        <div class="layui-header layui-bg-black">
            <div class="layui-logo" style="background-image: url(image/biaoti.jpg);background-size: cover;"></div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item" style="font-size: 25px;font-style: italic;"><i class="layui-icon layui-icon-util" style="font-size: 25px;"></i>学生后台信息管理系统</li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item" style="font-size: 20px;"><i class="layui-icon layui-icon-username" style="font-size: 20px;">&nbsp;</i><%=request.getSession().getAttribute("username")%></li>
                <li class="layui-nav-item"><a href="/logout" style="font-size: 17px;"><i class="layui-icon layui-icon-logout" style="font-size: 18px;"></i>&nbsp;退出</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ur class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed"><p style="font-size: 21px;">&nbsp;<i class="layui-icon layui-icon-set layui-anim layui-anim-rotate layui-anim-loop" style="font-size: 25px;"></i>基本功能</p></li>
                    <li class="layui-nav-item layui-nav-itemed"><a href="Student.jsp" style="font-size: 20px;" target="main_self_frame">&nbsp;&nbsp;&nbsp;<i class="layui-icon layui-icon-friends" style="font-size: 20px;"></i>&nbsp;&nbsp;学生管理</a></li>
                    <li class="layui-nav-item layui-nav-itemed"><a href="Teacher.jsp" style="font-size: 20px;" target="main_self_frame">&nbsp;&nbsp;&nbsp;<i class="layui-icon layui-icon-read" style="font-size: 20px;"></i>&nbsp;&nbsp;教师管理</a></li>
                    <li class="layui-nav-item layui-nav-itemed"><a href="Clazz.jsp" style="font-size: 20px;" target="main_self_frame">&nbsp;&nbsp;&nbsp;<i class="layui-icon layui-icon-home" style="font-size: 20px;"></i>&nbsp;&nbsp;班级管理</a></li>
                </ur>
            </div>
        </div>
        <div class="layui-body">
            <iframe src="main.html" name="main_self_frame" frameborder="0" class="layadmin-iframe" width="100%" height="100%" style="background-color: white;"></iframe>
        </div>


        <div class="layui-footer layui-bg-black">
            <p style="text-align: center;"><i class="layui-icon layui-icon-auz"></i> copy right 1364052596@qq.com</p>
        </div>
    </div>
    <script src="layui/layui.js"></script>
    <script src="layui/jquery-3.5.1.min.js"></script>
    <script>
        //JavaScript代码区域
        layui.use(['element', 'layer'], function(){
            var element = layui.element;
            var layer = layui.layer;
            layer.msg('欢迎来到后台管理界面！');
        });
    </script>
</body>
</html>
