<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <!--引入layui样式表文件-->
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css">
</head>
<body style="background-image: url(image/bg.jpg); background-size: cover;">
    <div class="layui-anim layui-anim-up">
        <div class="layui-container">
            <div class="layui-row">
                <br><br>
                <p><i class="layui-icon layui-icon-tree layui-anim layui-anim-rotate" style="font-size: 40px; color: white;"></i></p>
                <h2 style="color: white; display: inline; float: left;">欢迎来到学生信息管理系统，登录查看更多功能!</h2><h2 style="display: inline; float: right; color: white;" id="time"></h2>
                <hr class="layui-bg-gray">
            </div>
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md8">
                    <div class="layui-carousel" id="test1">
                        <div carousel-item>
                            <div><img src="image/guidasun.jpg" width="100%" height="550px"></div>
                            <div><img src="image/guidaxinkong.jpeg"  width="100%" height="550px"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4 layui-bg-black" style="opacity: 0.80;">
                    <br><br>
                    <p><center><i class="layui-icon layui-icon-release" style="font-size: 150px;"></i></center></p>
                    <center><p style="font-size: 30px;">Login</p></center>
                    <br><br><br>
                    <!-- 登录表单 -->
                    <form class="layui-form" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-user" style="font-size: 20px;"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input" lay-verType="tips" lay-reqText="请输入账号！" style="width: 81%;">
                            </div>
                        </div>
                        <br>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-password" style="font-size: 20px;"></i></label>
                            <div class="layui-input-block">
                                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" lay-verType="tips" lay-reqText="请输入密码！" style="width: 81%;">
                            </div>
                        </div>
                        <br>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-vercode" style="font-size: 20px;"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="vscode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input" lay-verType="tips" lay-reqText="请输入验证码！" style="width: 41%; display: inline-block;">
                                <img src="/DrawValidateCode" height="42px" width="39%" onclick="changeValidateCode()" id="ValidateCode" title="看不清楚？单击换一张！">
                            </div>
                        </div>
                        <br>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo" style="width: 38%;"><i class="layui-icon layui-icon-fire"></i>登录</button>
                                <button type="reset" class="layui-btn layui-btn-warm" style="width: 38%;"><i class="layui-icon layui-icon-refresh"></i>重置</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-row">
                    <hr class="layui-bg-gray">
                    <center><p style="color: white;"><i class="layui-icon layui-icon-auz"></i> copy right 1364052596@qq.com</p></center>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        // JS时间显示
        function displayTime(){
            var time = new Date();
            var strTime = time.toLocaleString();
            document.getElementById("time").innerHTML = strTime;
        }
        function start(){
            v = window.setInterval("displayTime()", 1000);
        }
        start();
        // 单击图片更换验证码
        function changeValidateCode() {
            var image = document.getElementById("ValidateCode");
            var date = new Date().getTime();
            image.src = "/DrawValidateCode?" +date;
        }
    </script>
    <!-- js文件引入 -->
    <script type="text/javascript" src="layui/layui.js"></script>
    <!-- 模块引入 -->
    <script type="text/javascript">
        layui.use(['carousel', 'element', 'form'], function(){
            var carousel = layui.carousel;
            var element = layui.element;
            var form = layui.form;
            const $ = layui.$;
            carousel.render({
                elem: '#test1',
                width: '100%',
                height: '550px',
                arrow: 'hover'
            });
            form.on('submit(formDemo)', function(data){
                var index = layer.load(0, {shade: [0.5, '#000000']});
                $.ajax({
                    url:'/login',
                    type: 'POST',
                    data: JSON.stringify(data.field),
                    success: function (res){
                        layer.close(layer.index);
                        if (res.code === 200) {
                            window.location.href = 'index.jsp';
                        } else if(res.code === 300){
                            layer.msg('验证码错误！请重新输入！', {icon: 5});
                            changeValidateCode();
                        } else if(res.code === 400){
                            layer.msg('用户名不存在！请重新输入', {icon: 5});
                            changeValidateCode();
                        }else if(res.code === 500) {
                            layer.msg('密码错误！请重新输入！', {icon: 5});
                            changeValidateCode();
                        }else {
                            layer.msg('未知错误！', {icon: 5});
                        }
                    },
                    error: function (error){
                        layer.msg(error);
                    }
                });
                return false;
            });
        });
    </script>
</body>
</html>
