<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息添加</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
    <form class="layui-form">
        <div class="layui-card-body">
            <div class="layui-form-item">
                <label class="layui-form-label">班级名称：</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" id="name" name="name" lay-verify="required" lay-verType="tips" lay-reqText="请输入班级名称！" style="border-color: dodgerblue; width: 50%;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级信息：</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" id="information" lay-verify="required" name="information" lay-verType="tips" lay-reqText="请输入班级信息！" style="border-color: dodgerblue;">
                </div>
            </div>
            <div class="layui-form-item layui-hide">
                <button type="button" lay-submit="" lay-filter="user-add-save" id="user-add-save" class="layui-btn">
                    确认
                </button>
            </div>
        </div>
    </form>
    <script src="layui/layui.all.js"></script>
</body>
</html>
