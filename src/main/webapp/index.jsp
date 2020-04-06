<%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/11/14
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
<script>
    $(function () {

        var i = 5;
        var timer = setInterval(function () {

            i--;
            $("sec").html(i);
            if (i == 0) {
                location.href = "http://localhost:8088/wsx/home.html";
            }
        },1000);

    })
</script>
</head>
<body>
<div style="text-align: center">
<h1>Java课设：团员信息管理系统</h1>
<h2>完成人：王舒啸，庞陈勃，李红江</h2>
<br><br><br>

<h3 ><sec>5</sec>秒后进行跳转</h3>
</div>
</body>
</html>