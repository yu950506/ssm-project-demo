<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="${AppPath}/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-body">
        <div class="layui-container">
            <h1>没有权限</h1>
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
    });
</script>
</body>
</html>