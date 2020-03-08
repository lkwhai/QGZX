<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beam.Human" %><%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/9/23
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="QueryInfo.jsp" %>
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>


<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

    <script>
        //在页面加载完成后
        $(function () {
            //添加失焦事件
            //给username绑定blur事件
            $("#date").blur(function () {

                //获取id=username的文本输入值
                var date = $("#date").val();
                if (date == ""){
                    $("#date_message").html("不能为空").css("color","red");
                }else {
                    $("#date_message").html("正确").css("color","green");
                }
            });
            $("#name").blur(function () {

                //获取id=username的文本输入值
                var name = $("#name").val();
                if (name == ""){
                    $("#name_message").html("不能为空").css("color","red");
                }else {
                    $("#name_message").html("正确").css("color","green");
                }
            });
            $("#location").blur(function () {
                //获取id=username的文本输入值
                var location = $("#location").val();
                if (location == ""){
                    $("#location_message").html("不能为空").css("color","red");
                }else {
                    $("#location_message").html("正确").css("color","green");
                }
            });
            $("#substance").blur(function () {
                //获取id=username的文本输入值
                var substance = $("#substance").val();
                if (substance == ""){
                    $("#substance_message").html("不能为空").css("color","red");
                }else {
                    $("#substance_message").html("正确").css("color","green");
                }
            });
        });
    </script>
    <title>新增学生信息</title>
</head>


<body>
<form action="addServlet" method="post" >
    <center>
    <table class="gridtable">
        <tr>
            <td align="center" colspan="2">添加学生信息</td>
        </tr>
        <tr>
            <td>工作人员：
            <c:if test="${sessionScope.status=='user'}">
                <td>${sessionScope.user.getUsername()}</td>
            </c:if>
            <c:if test="${sessionScope.status=='admin'}">
                <td><input name="name" type="text" id="name"><label id="name_message" ></label></td>
            </c:if>
            </td>
        </tr>
        <tr>
            <td>工作日期：</td><td><input name="date" type="text" id="date"><label id="date_message" ></label> </td>
        </tr>
        <tr>
            <td>工作周：</td><td><input name="week" type="number" step="1" min="1" max="20"></td>
        </tr>
        <tr>
            <td>工作内容：</td><td><input name="substance" type="text" id="substance"><label id="substance_message"></label></td>
        </tr>
        <tr>
            <td>工作位置：</td><td><input name="location" type="text" id="location"><label id="location_message"></label></td>
        </tr>
        <tr>
            <td>工作时长：</td><td><input name="duration" type="number" step="0.5" min="0" max="8"></td>
        </tr>
        <tr>
            <td align="center" colspan="2">
            <input type="submit" value="提交"><input type="reset" value="重置">
            </td>
        </tr>
    </table>
    </center>
    </form>

</body>
</html>
