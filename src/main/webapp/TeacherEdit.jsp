<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息编辑</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<form class="layui-form">
    <div class="layui-card-body">
        <div class="layui-form-item">
            <label class="layui-form-label">教师ID：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="id" name="id" readonly style="border: none;">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教师姓名：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="name" lay-verify="required" name="name" lay-verType="tips" lay-reqText="请输入教师姓名！" style="border-color: green; width: 50%;">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教师性别：</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" id="man" value="男" title="男" checked>
                <input type="radio" name="sex" id="woman" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教师手机：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="mobile" lay-verify="required" name="mobile" lay-verType="tips" lay-reqText="请输入教师手机！" style="border-color: green;">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教师邮箱：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" id="email" lay-verify="required" name="email" lay-verType="tips" lay-reqText="请输入教师邮箱！" style="border-color: green;">
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
<script>
    layui.use('form', function(){
        var form = layui.form;
        var parent_json = parent.json;
        data = JSON.parse(parent_json);
        const $ = layui.$;
        if (data.sex === '男'){
            $("#man").attr("checked","checked");
        }else{
            $("#woman").attr("checked","checked");
        }
        form.render('radio');
    });
</script>
</body>
</html>
