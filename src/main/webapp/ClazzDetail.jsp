<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级信息查看</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
    <div class="layui-card-body">
        <div class="layui-form-item">
            <label class="layui-form-label">班级ID：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="id" readonly style="border: none;">
            </div>
        </div>
        <hr class="layui-bg-black">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="name" readonly style="border: none;">
            </div>
        </div>
        <hr class="layui-bg-black">
        <div class="layui-form-item">
            <label class="layui-form-label">班级信息：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="information" readonly style="border: none;">
            </div>
        </div>
        <hr class="layui-bg-black">
    </div>
</body>
</html>
