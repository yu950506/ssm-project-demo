<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>权限管理页面</title>
    <link rel="stylesheet" href="${AppPath}/static/layui/css/layui.css">
</head>
<%-- 使用弹层组件弹出添加权限表单--%>
<form class="layui-form" id="addPermission" lay-filter="updatePermission"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
    <div class="layui-form-item" id="hideId">
        <label class="layui-form-label">权限id</label>
        <div class="layui-input-block">
            <input type="text" name="id" placeholder="请输入权限名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限名称</label>
        <div class="layui-input-block">
            <input type="text" name="permissionName" placeholder="请输入权限名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限表达式</label>
        <div class="layui-input-block">
            <input type="text" name="permissionUrl" placeholder="请输入权限表达式" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限描述</label>
        <div class="layui-input-block">
            <input type="text" name="permissionDesc" placeholder="请输入权限描述" autocomplete="off" class="layui-input">
        </div>
    </div>
</form>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%--引入头部数据--%>
    <jsp:include page="../head.jsp"/>
    <%--引入左侧菜单栏数据--%>
    <jsp:include page="../nav.jsp"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <%--开启头部工具栏--%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-group demoTable">
                <button class="layui-btn layui-btn-normal" data-type="add">添加</button>
                <button class="layui-btn layui-btn-danger" data-type="delete">删除</button>
                <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
                <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
                <button class="layui-btn" data-type="isAll">验证是否全选</button>
            </div>
        </script>
        <%--表格数据--%>
        <table id="permissionTable" lay-filter="permissionTableFilter">
        </table>
        <%--每一个行的操作事件--%>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>
    <jsp:include page="../foot.jsp"/>
    <script src="${AppPath}/static/jquery/jquery-3.2.1.min.js"></script>
    <script src="${AppPath}/static/layui/layui.js"></script>
    <script>
        var maxPage; // 最大页
        var currPage;// 当前页
        $("#addPermission").hide();
        //JavaScript代码区域
        layui.use(['element', 'table', 'form'], function () {
            var element = layui.element,
                form = layui.form,
                table = layui.table;
            // 页面一加载就渲染权限表格数据
            var permissionTable = table.render({
                title: '权限信息表', // 表格的标题
                elem: '#permissionTable', // 表格的id
                url: '${AppPath}/permission/findAll',//请求的数据接口
                page: true,//开启分页
                toolbar: "#toolbarDemo", // 表格表头工具栏
                even: true,//开启隔行背景
                cols: [[ //表头，字段要和后台传递的数据字段一致
                    {type: 'checkbox', fixed: 'left'},
                    {field: 'id', title: 'ID', width: '10%', sort: true, align: 'center'},
                    {field: 'permissionName', title: '权限名称', width: '10%', align: 'center'},
                    {field: 'permissionUrl', title: '权限表达式', width: '20%', sort: true, align: 'center'},
                    {field: 'permissionDesc', title: '权限描述', width: '20%', align: 'center'},
                    {title: '操作', toolbar: '#barDemo', width: '20%', align: 'center'} // 设置每一行数据的操作
                ]],
                request: { // 请求参数
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'pageSize' //每页数据量的参数名，默认：limit
                },
                response: { // 响应数据
                    statusName: 'code' //规定数据状态的字段名称，默认：code
                    , statusCode: 200 //规定成功的状态码，默认：0，我的自定义成功是200，所以进行修改
                    , msgName: 'msg' //规定状态信息的字段名称，默认：msg
                    , countName: 'count' //规定数据总数的字段名称，默认：count
                    , dataName: 'data' //规定数据列表的字段名称，默认：data
                },
                parseData: function (res) { //res 即为原始返回的数据
                    maxPage = res.data.total + 1; // 最大页永远不可能总记录数+1
                    currPage = res.data.pageNum; // 当前页
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.data.total, //解析数据长度
                        "data": res.data.list //解析数据列表
                    };
                },
            });
            //监听每一条数据的工具条
            //语法：table.on('event(filter)', callback); 注：event为内置事件名，filter为容器lay-filter设定的值
            table.on('tool(permissionTableFilter)', function (obj) {
                var data = obj.data;
                //   <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
                if (obj.event === 'detail') { // 查看详情
                    layer.msg('ID：' + data.id + ' 的查看操作');
                } else if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) { // 删除当前的数据
                        // 发送请求到后台删除数据
                        $.post("${AppPath}/permission/deletePermissionById", {"id": data.id}, function (res) {
                            if (200 == res.code) {
                                tableReloadCurrentPage();
                                obj.del();
                                layer.close(index);
                            } else {
                                layer.msg("删除失败");
                            }
                        })
                    });
                } else if (obj.event === 'edit') { // 修改数据
                    // 更新数据
                    $("#hideId").show(); // 显示权限的id,为了通过后台修改该id的数据
                    // 1. 将数据显示在表单里面
                    layer.open({
                        type: 1,
                        title: '权限更新',
                        area: ['500px'],//area - 宽高
                        skin: 'layui-layer-rim', //加上边框
                        scrollbar: false,// 屏蔽滚动条
                        content: $('#addPermission'),
                        success: function () { // success是弹框加载完成之后执行的函数
                            // form.val('filter', object);用于给指定表单集合的元素赋值和取值。如果 object 参数存在，则为赋值；如果 object 参数不存在，则为取值。
                            form.val('updatePermission', { // 将权限数据显示在表格中
                                "id": data.id,
                                "permissionName": data.permissionName,
                                "permissionUrl": data.permissionUrl,
                                "permissionDesc": data.permissionDesc,
                            });
                        },
                        btn: ['更新', '关闭'], //可以无限个按钮
                        yes: function (index, layero) { // yes按钮1回调函数
                            // 2. 获取修改后的表单数据
                            var updatePermissionData = $("#addPermission").serialize();
                            $.post("${AppPath}/permission/updatePermission", updatePermissionData, function (res) {
                                if (200 == res.code) { // 更新成功
                                    tableReloadCurrentPage();
                                    layer.close(index);
                                } else {
                                    layer.msg("更新失败");
                                }
                            });
                            // 3. 将数据提交到后台
                            // 4. 表格重载到当前页
                        },
                        btn2: function (index, layero) { // btn2是按钮2的回调函数
                            layer.close(index);// 关闭模态框
                        }
                    })

                }
            });

            // 表头工具栏事件
            var $ = layui.$, active = {
                add: function () {
                    $("#addPermission")[0].reset();
                    $("#hideId").hide();
                    layer.open({
                        type: 1,
                        title: '权限添加',
                        area: ['500px'],//area - 宽高
                        skin: 'layui-layer-rim', //加上边框
                        scrollbar: false,// 屏蔽滚动条
                        content: $('#addPermission'),
                        btn: ['添加', '重置'], //可以无限个按钮
                        yes: function (index, layero) { // yes按钮1回调函数
                            //1. 获取表达数据
                            var permissionData = $("#addPermission").serialize();
                            //2. 使用AJAX将数据提交到后台
                            $.post("${AppPath}/permission/addPermission", permissionData, function (res) {
                                if (200 == res.code) { // 添加成功
                                    // 3. 关闭框框，
                                    layer.close(index);
                                    // 4. 表单数据重置
                                    $("#addPermission")[0].reset();
                                    // 5. 重新渲染表格数据,跳转到最后一页，方便用户查看新添加的数据
                                    tableReloadMaxPage();
                                } else {
                                    layer.msg("添加失败!提示失败原因");
                                }
                            });
                        },
                        btn2: function (index, layero) { // btn2是按钮2的回调函数
                            console.info("重置了表单数据");
                            $("#addPermission")[0].reset();
                            return false; // 不关闭框框
                        }
                    })
                },
                delete: function () { // 获取选中的数据
                    var checkStatus = table.checkStatus('permissionTable')
                        , data = checkStatus.data;
                    if (data.length > 0) {
                        // 获取选中勾选数据的id
                        var ids = new Array();
                        data.forEach(function (permission) {
                            ids.push(permission.id);
                        })
                        // 将id提交到后台进行批量删除
                        $.post("${AppPath}/permission/deletePermissionByIds", {"ids": ids.join(",")}, function (res) {
                            if (200 == res.code) {
                                // 删除成功！表格数据重新加载
                                tableReloadCurrentPage();
                            } else {
                                layer.msg("批量删除失败");
                            }
                        })
                    } else {
                        layer.msg('请勾选要删除的数据');
                    }
                },
                getCheckData: function () { //获取选中数据
                    //语法：table.checkStatus('ID')，其中 ID 为基础参数 id 对应的值（见：设定容器唯一ID），如：
                    var checkStatus = table.checkStatus('permissionTable')
                        , data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                }
                , getCheckLength: function () { //获取选中数目
                    var checkStatus = table.checkStatus('permissionTable')
                        , data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                }
                , isAll: function () { //验证是否全选
                    var checkStatus = table.checkStatus('permissionTable');
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                }
            };

            // todo 3 使用委托事件来解决表单数据重载之后，按钮失效的功能
            $('body').on('click', '.demoTable .layui-btn', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
            });

            // 定义函数：表格数据重载，跳转到当前页
            function tableReloadCurrentPage() {
                permissionTable.reload({
                    page: {
                        curr: currPage
                    }
                })
            }

            // 定义函数：表格数据重载，跳转到最大页
            function tableReloadMaxPage() {
                permissionTable.reload({
                    page: {
                        curr: maxPage
                    }
                })
            }
        });
    </script>
</body>


</html>