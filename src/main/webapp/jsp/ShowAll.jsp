<%--
  Created by IntelliJ IDEA.
  User: wangshuxiao
  Date: 2019/12/28
  Time: 09:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Showall</title>

    <script>
        function delMember(uid) {
            if (confirm("Are you serious ? there is no back up..."))
                location.href = "${pageContext.request.contextPath}/member/deleteById?row=${pageList.row}&current=${pageList.current}&id="+uid;
        }
    </script>

</head>
<body style="background:transparent;">
<div class="container-fluid">
    <div>
        <div class="row" style="margin-top: -10px">
            <div style="font: bold 40px 'Arial Black';text-align: center;">全部团员信息</div>
        </div>
    </div>
    <div class="row">
    <table class="table table-hover">
        <tr>
            <th><input type="checkbox" id="firstid"></th>
            <th>编号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
            <th>院系</th>
            <th>专业</th>
            <th>班级</th>
            <th>辅导员</th>
            <th>入团时间</th>
            <th>操作</th>
        </tr>
        
        
        <form >
            <c:forEach items="${requestScope.pageList.list}" var="member" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="checkbox" value="${member.id}"></td>
                    <td>${s.count}</td>
                    <td>${member.sno}</td>
                    <td>${member.name}</td>
                    <td>${member.age}</td>
                    <td>${member.sex}</td>
                    <td>${member.sdept}</td>
                    <td>${member.major}</td>
                    <td>${member.className}</td>
                    <td>${member.assistant}</td>
                    <td>${member.joinDate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/member/findMember?id=${member.id}">
                            <button type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            </button>
                        </a>
                        <a href="Javascript:delMember(${member.id})">
                            <button type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </form>

    </table>
    </div>

    <div class="row" style="text-align: center;font-size: 25px">
        一共${pageList.total}条记录，共${pageList.pages}页。
    </div>

    <div class="row" style="float: left">
        <nav aria-label="Page navigation">
            <ul class="pagination pagination-lg">

                <c:if test="${pageList.current == 1}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=1&ob=${ob}&com=${com}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${pageList.current != 1}">
                    <li >
                        <a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=1&ob=${ob}&com=${com}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="1" end="${pageList.pages}" step="1">
                    <c:if test="${pageList.current == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=${i}&ob=${ob}&com=${com}">${i}</a></li>
                    </c:if>

                    <c:if test="${pageList.current != i}">
                        <li><a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=${i}&ob=${ob}&com=${com}">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pageList.current == pageList.pages}">
                    <li class="disabled">
                        <a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=${pageList.pages}&ob=${ob}&com=${com}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${pageList.current != pageList.pages}">
                    <li>
                        <a href="${pageContext.request.contextPath}/member/listshow?row=${pageList.row}&current=${requestScope.pageList.pages}&ob=${ob}&com=${com}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>


            </ul>
        </nav>

    </div>


</div>
</body>
</html>