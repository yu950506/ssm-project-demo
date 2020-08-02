<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>sql监控页面</title>
    <link rel="stylesheet" href="${AppPath}/static/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%--引入头部数据--%>
    <jsp:include page="../head.jsp"/>
    <%--引入左侧菜单栏数据--%>
    <jsp:include page="../nav.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div id="div1">

        </div>
    </div>
    <script>
        //JavaScript代码区域
        layui.use(['element', 'layer'], function () {
            var element = layui.element;
            var layer = layui.layer;
            var index = layer.open({
                type: 2,
                content: '${AppPath}/druid/index.html',
                area: ['800px', '800px'],
                scrollbar: false,//关闭滚动条
                fixed: false,
                maxmin: true
            });
            //弹出即全屏
            layer.full(index);
        });
    </script>
</body>
</html>