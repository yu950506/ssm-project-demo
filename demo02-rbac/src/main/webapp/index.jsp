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
    <%--引入头部数据--%>
    <jsp:include page="pages/head.jsp"/>
    <%--引入左侧菜单栏数据--%>
    <jsp:include page="pages/nav.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">内容主体区域

            <i class="layui-icon layui-icon-face-smile" style="font-size: 30px; color: #1E9FFF;"></i>

        </div>
    </div>

    <%--映入底部数据--%>
    <jsp:include page="pages/foot.jsp"/>
</div>
<script src="${AppPath}/static/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>
</body>
</html>