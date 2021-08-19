<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>班级信息管理界面</title>
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
    <br>
    <center><p style="color: black; font-size: 30px;">班级信息管理</p></center>

    <table class="layui-hide" id="test" lay-filter="demo"></table>


    <script src="layui/layui.js"></script>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container layui-inline">
            <button class="layui-btn layui-btn-normal" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加</button>
            <button class="layui-btn layui-btn-danger" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</button>
            <button class="layui-btn layui-btn-warm" lay-event="refreshTable"><i class="layui-icon layui-icon-refresh-1"></i>刷新</button>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" class="layui-input" id="info">
            </div>
            <button class="layui-btn" lay-event="search" style="background-image: url('image/firework.gif'); background-size: cover;"><i class="layui-icon layui-icon-search"></i>搜索</button>
        </div>
    </script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs layui-btn-radius" lay-event="detail"><i class="layui-icon layui-icon-search"></i>查看</a>
        <a class="layui-btn layui-btn-xs layui-btn-radius" lay-event="edit"><i class="layui-icon layui-icon-list"></i>编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs layui-btn-radius" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
    </script>
    <script>
        layui.use(['table', 'layer'], function(){
            var layer = layui.layer;
            var table = layui.table;
            const $ = layui.$;

            const tableIns = table.render({
                text:{none: '无数据，请刷新！'}
                ,elem: '#test'
                ,height: 'full-77'
                ,page: true
                ,url:'/ClazzAll'
                ,response: {
                    statusCode : 200
                }
                ,toolbar: '#toolbarDemo'
                ,cols: [[
                    {fixed:'left', type:'checkbox', width: '5%'}
                    ,{field:'id', width: '10%', title: '班级ID', sort: true, align:'center', fixed:'left'}
                    ,{field:'name', width: '15%', title: '班级名称', sort: true, align:'center'}
                    ,{field:'information', width: '70%', title: '班级信息'}
                    ,{fixed: 'right', width: '20%', title: '基本操作', align:'center', toolbar: '#barDemo'}
                ]]
            });

            table.on('tool(demo)', function (obj) {
                var data = obj.data;
                if(obj.event === 'detail'){
                    layer.open({
                        anim: 6
                        ,title: '班级信息查看'
                        ,type: 2
                        ,content: 'ClazzDetail.jsp'
                        ,area:['600px', '260px'],
                        success : function(layero, index){
                            const body = layer.getChildFrame("body", index);
                            body.find("#id").val(data.id);
                            body.find("#name").val(data.name);
                            body.find("#information").val(data.information);
                        }
                    });
                } else if(obj.event === 'del'){
                    layer.confirm('您确定要删除该条数据吗？', function (index) {
                        $.ajax({
                            url: '/delClazz',
                            type: 'POST',
                            data: {id: data.id},
                            success: function (res) {
                                if (res.code === 200){
                                    tableIns.reload();
                                    layer.msg('删除成功！');
                                }else{
                                    layer.msg('删除失败！');
                                }
                            },
                            error: function (error) {
                                layer.msg(error);
                            }
                        });
                        layer.close(index);
                    });
                } else if(obj.event === 'edit'){
                    layer.open({
                        skin: 'layui-layer-molv'
                        ,anim: 6
                        ,title: '班级信息编辑'
                        ,type: 2
                        ,content: 'ClazzEdit.jsp'
                        ,btn: ['确定', '取消']
                        ,area:['600px', '280px'],
                        yes:function (index, layero) {
                            const iframeWindow = window['layui-layer-iframe' + index]
                                , submitID = 'user-add-save'
                                , submit = layero.find('iframe').contents().find('#' + submitID);

                            iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                                const field = data.field;
                                $.ajax({
                                    url:'/editClazz',
                                    type: 'POST',
                                    data: JSON.stringify(field),
                                    success: function (res){
                                        if (res.code === 200) {
                                            tableIns.reload();
                                            layer.close(index);
                                            layer.msg('编辑成功!');
                                        } else {
                                            layer.msg('编辑失败!');
                                        }
                                    },
                                    error: function (error){
                                        layer.msg('error');
                                    }
                                });
                            });
                            submit.trigger('click');
                        },
                        success: function (layero, index) {
                            const body = layer.getChildFrame("body", index);
                            body.find("#id").val(data.id);
                            body.find("#name").val(data.name);
                            body.find("#information").val(data.information);
                        }
                    });
                }
            });

            table.on('toolbar(demo)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'refreshTable':
                        location.reload();
                        break;
                    case 'add':
                        layer.open({
                            skin: 'layui-layer-lan'
                            ,anim: 6
                            ,title: '班级信息添加'
                            ,type: 2
                            ,content: 'ClazzAdd.jsp'
                            ,btn: ['确定', '取消']
                            ,area:['600px', '220px'],
                            yes: function (index, layero) {
                                const iframeWindow = window['layui-layer-iframe' + index]
                                    , submitID = 'user-add-save'
                                    , submit = layero.find('iframe').contents().find('#' + submitID);

                                iframeWindow.layui.form.on('submit(' + submitID + ')', function (data) {
                                    const field = data.field;
                                    $.ajax({
                                        url: '/addClazz',
                                        type: 'POST',
                                        data: JSON.stringify(field),
                                        success: function (res) {
                                            if(res.code === 200){
                                                tableIns.reload();
                                                layer.close(index);
                                                layer.msg('添加成功！')
                                            }else{
                                                layer.msg('添加失败！');
                                            }
                                        },
                                        error: function (error) {
                                            layer.msg(error);
                                        }
                                    });
                                });
                                submit.trigger('click');
                            }
                        });
                        break;
                    case 'del':
                        var data = checkStatus.data;
                        if (data.length > 0){
                            layer.confirm('您确定要删除所选数据吗？', function (index) {
                                $.ajax({
                                    url: '/delClazzs',
                                    type: 'POST',
                                    data: JSON.stringify(data),
                                    success: function (res) {
                                        if(res.code === 200){
                                            tableIns.reload();
                                            layer.msg('删除成功！')
                                        }else{
                                            layer.msg('删除失败！');
                                        }
                                    },
                                    error: function (error) {
                                        layer.msg(error);
                                    }
                                });
                                layer.close(index);
                            });
                        }else{
                            layer.msg("您至少应该选择一条数据！");
                        }
                        break;
                    case 'search':
                        var data = document.getElementById('info').value;
                        if(data.replace(/(^\s*)|(\s*$)|(\n)/g, "").length > 0){
                            data = data.replace(/^\s*|\s*$/g,"");
                            layer.msg('查询成功！')
                            tableIns.reload({
                                url: '/searchClazzs?info='+data
                            })
                        }else {
                            layer.msg('请不要填入空数据！');
                        }
                        break;
                }
            });

        });
    </script>

</body>
</html>
