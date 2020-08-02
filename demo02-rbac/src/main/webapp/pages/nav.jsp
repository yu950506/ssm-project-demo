<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test" id="navList">
            <%--使用Ajax动态渲染数据--%>

        </ul>
    </div>
</div>
<script src="${AppPath}/static/jquery/jquery-3.2.1.min.js"></script>
<script src="${AppPath}/static/layui/layui.js"></script>
<script>
    // 1. 发送AJAX请求，获取所有左侧导航栏数据
    $(function () {
        $.ajax({
            url: "${AppPath}/oneList/findAll",
            async: false,
            success: function (res) {
                if (200 == res.code) {
                    // 2. 遍历所有的1级列表数据
                    res.data.forEach(function (oneList) {
                        var li = $("<li class='layui-nav-item'></li>");
                        var i = $("<i class='layui-icon'></i>").append(oneList.icon + "   ").append(oneList.name);
                        var a = $("<a></a>").attr("href", "javascript:;").append(i);
                        a.append(i);
                        li.append(a);
                        var dl = $("<dl class='layui-nav-child'></dl>")
                        //3. 遍历所有的2级列表数据
                        oneList.twoLists.forEach(function (twoList) {
                            var dd = $("<dd></dd>");
                            var i = $("<i class='layui-icon'></i>").append(twoList.icon + "   ").append(twoList.name)
                            var url = "${AppPath}" + twoList.url;
                            var a = $("<a></a>").attr("href", url).append(i);
                            dd.append(a);
                            dl.append(dd);
                        })
                        if (oneList.twoLists.length > 0) {
                            li.append(dl);
                        }
                        $("#navList").append(li);
                    })
                    // todo 1 layui渲染二级菜单注意事项
                    layui.use('element', function () {
                        var element = layui.element;
                        element.render('nav', 'test'); //对 lay-filter="test1" 所在导航重新渲染。注：layui 2.1.6 版本新增
                    });
                } else {
                    alert("导航数据异常！")
                }
            }
        })
    });

</script>
