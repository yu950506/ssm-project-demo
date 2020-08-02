<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录页</title>
    <link rel="stylesheet" href="${AppPath}/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-body">
        <div class="layui-container">
            <div class="layui-row layui-anim layui-anim-scale">
                <div class="layui-col-md4 layui-col-lg-offset4">
                    <div class="layui-card">
                        <div class="layui-card-header">用户登录：</div>
                    </div>
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" lay-verify="required" placeholder="请输入用户名"
                                       autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="password" lay-verify="required" placeholder="请输入密码"
                                       autocomplete="off"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button type="submit" class="layui-btn" lay-submit="" lay-filter="userLogin">登录</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script src="${AppPath}/static/jquery/jquery-3.2.1.min.js"></script>
<script src="${AppPath}/static/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'form'], function () {
        var element = layui.element;
        var form = layui.form;
//监听提交
        form.on('submit(userLogin)', function (data) {
            $.post("${AppPath}/users/userLogin", data.field, function (res) {
                if (200 == res.code) {
                    // 登录成功！跳转到主页
                    window.location.href = "${AppPath}";
                } else {
                    layer.alert(res.data);
                }
            })
            return false;
        });
    });
</script>
</body>
</html>