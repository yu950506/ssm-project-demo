<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户管理页面</title>
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
        <%--表格显示用户数据--%>
        <table id="logTable" lay-filter="logTable">
            <%--使用js方法级别的渲染数据--%>
        </table>
    </div>


    <%--映入底部数据--%>
    <jsp:include page="../foot.jsp"/>
    <script src="${AppPath}/static/layui/layui.js"></script>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-group demoTable">
            <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
            <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
            <button class="layui-btn" data-type="isAll">验证是否全选</button>
        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script src="${AppPath}/static/jquery/jquery-3.2.1.min.js"></script>
</body>
</html>

<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
    layui.use(['table', 'form', 'element'], function () {
        var table = layui.table;
        var form = layui.form;
        var element = layui.element;
        // 渲染表格
        var tableRender = table.render({
            even: true,//开启隔行背景
            elem: '#logTable',
            url: '${AppPath}/syslog/findAll',
            request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                // 重新设置状态码：默认是0,如果和后台数据接口数据不一致数据就不会显示
                statusCode: 200
            },
            toolbar: '#toolbarDemo',
            title: '日志数据表',
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                , curr: 1 //设定初始在第 5 页
                , groups: 5 //只显示 1 个连续页码
                , first: true //不显示首页
                , last: true //不显示尾页
            },
            cols: [[
                {type: 'checkbox'},
                {field: 'id', title: 'ID', sort: true, align: 'center', width: "5%"},
                {field: 'username', title: '操作人', align: 'center', width: "10%"},
                {field: 'ip', title: 'ip', align: 'center', width: "10%"},
                {field: 'url', title: 'url', sort: true, align: 'center', width: "10%"},
                {field: 'requestType', title: '请求类型', sort: true, align: 'center', width: "8%"},
                //  {field: 'requestParam', title: '请求参数', sort: true, align: 'center', width: "10%"},
                {field: 'clazz', title: '访问的类', align: 'center', width: "10%"},
                {field: 'method', title: '访问的方法', sort: true, align: 'center', width: "10%"},
                {field: 'visitTimeStr', title: '访问时间', align: 'center', width: "15%"},
                {field: 'executionTime', title: '执行时长(ms)', sort: true, align: 'center', width: "10%"},
                {fixed: 'right', title: '操作', align: 'center', width: "15%",toolbar: "#barDemo"}
            ]],
            parseData: function (res) {
                maxPage = res.data.total + 1;
                currentPage = res.data.pageNum;
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            }
        });
        //监听工具条
        //table.on('event(filter)', callback); //事件监听。event为内置事件名（详见下文），filter为容器lay-filter设定的值
        table.on('tool(logTable)', function (obj) {
            var data = obj.data;
            // 用户查看详情功能
            if (obj.event === 'detail') {
                layer.msg('ID：' + data.id + ' 的查看操作');
            } else if (obj.event === 'del') { // 用户删除功能
                layer.confirm('真的删除行么', function (index) {
                    var userId = data.id;
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') { // 用户编辑功能
                layer.alert('编辑行：<br>' + JSON.stringify(data))
            }
        });

        //表头工具栏事件
        var $ = layui.$, active = {
            getCheckData: function () { //获取选中数据
                var checkStatus = table.checkStatus('userTable')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            , getCheckLength: function () { //获取选中数目
                var checkStatus = table.checkStatus('userTable')
                    , data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            }
            , isAll: function () { //验证是否全选
                var checkStatus = table.checkStatus('userTable');
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
            },

        };

        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

</script>
