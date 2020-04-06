<%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/12/28
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--这个东西得是必须加上的，否则的话，会出现错误。--%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <link href="../css/bootstrap.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <!--fonts-->
    <link href='http://fonts.useso.com/css?family=Raleway:500,600,700,100,800,900,400,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Ubuntu:400,500,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Voltaire' rel='stylesheet' type='text/css'>
    <!--//fonts-->
    <script src="../js/jquery.min.js"> </script>
    <script src="../js/bootstrap.min.js"></script>
    <title>update</title>

    <script>

    </script>

</head>
<body style="background:transparent;">
    <div class="container">
        <div class="row">
            <h1 style="margin-left: 35%">团员信息更改</h1>
        </div>
        <div class="row">
            <div class="col-md-3" ></div>
            <div class="col-md-5">
                <form action="${pageContext.request.contextPath}/member/update?id=${member.id}&sno=${member.sno}&name=" method="post">
                    <div class="form-group">
                        <label for="sno">学号</label>
                        <c:if test="${member.sno != null}">
                            <input disabled="disabled" type="text" class="form-control" id="sno" name="sno" placeholder="学号" value="${member.sno}">
                        </c:if>

                        <c:if test="${member.sno == null}">
                            <input type="text" class="form-control" id="sno" name="sno" placeholder="学号" value="${member.sno}">
                        </c:if>
                    </div>

                    <div class="form-group ">
                        <label for="name">姓名</label>
                        <c:if test="${member.name != null}">
                            <input disabled="disabled" type="text" class="form-control" id="name" name="name" placeholder="姓名" value="${member.name}">
                        </c:if>

                        <c:if test="${member.name == null}">
                            <input  type="text" class="form-control" id="name" name="name" placeholder="姓名" value="${member.name}">
                        </c:if>

                    </div>


                    <div>

                        <label for="inlineRadio1">性别</label><br>
                        <label class="radio-inline">
                            <c:if test="${member.sex == '男'}">
                                <input type="radio" name="sex" checked id="inlineRadio1" value="男"> 男
                            </c:if>
                            <c:if test="${member.sex != '男'}">
                                <input type="radio" name="sex"  id="inlineRadio1" value="男"> 男
                            </c:if>
                        </label>
                        <label class="radio-inline">
                            <c:if test="${member.sex != '男'}">
                                <input type="radio" checked name="sex"  id="inlineRadio2" value="女"> 女
                            </c:if>

                            <c:if test="${member.sex == '男'}">
                                <input type="radio" name="sex"  id="inlineRadio2" value="女"> 女
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <label for="age">年龄</label>
                        <input type="text" class="form-control" id="age" name="age" placeholder="年龄" value="${member.age}">
                    </div>

                    <div class="form-group">
                        <label for="sdept">院系</label>
                        <input type="text" class="form-control" id="sdept" name="sdept" placeholder="院系" value="${member.sdept}">
                    </div>

                    <div class="form-group">
                        <label for="major">专业</label>
                        <input type="text" class="form-control" id="major" name="major" placeholder="专业" value="${member.major}">
                    </div>

                    <div class="form-group">
                        <label for="className">班级</label>
                        <input type="text" class="form-control" id="className" name="className" placeholder="className" value="${member.className}">
                    </div>

                    <div class="form-group">
                        <label for="assistant">辅导员</label>
                        <input type="text" class="form-control" id="assistant" name="assistant" placeholder="辅导员" value="${member.assistant}">
                    </div>

                    <div class="form-group">
                        <label for="joinDate">入团时间</label>
                        <input type="text" class="form-control" id="joinDate" name="joinDate" placeholder="入团时间" value="${member.joinDate}">
                    </div>

                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
            </div>
            <div class="col-md-4" ></div>
        </div>


    </div>
</body>
</html>