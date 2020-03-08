<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="beam.Human" %>
<%@ page import="dao.HumanDao" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/9/20
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<html>
<script type="text/css">
    td,th{
        text-align: center;
    }
</script>

<head>
    <title>根据名字查询信息</title>
</head>

<table class="table table-bordered" align='center' style="width: 1000px;margin-top: 20px;text-align: center;">
        <tr style="height: 40px;">
            <th style="width:10%;text-align: center;">姓名</th>
            <th style="width:10%;text-align: center;">工作时间</th>
            <th style="width:10%;text-align: center;">工作周</th>
            <th style="width:10%;text-align: center;">工作位置</th>
            <th style="width:10%;text-align: center;">工作内容</th>
            <th style="width:10%;text-align: center;">工作时长</th>
            <th style="width:10%;text-align: center;">操作</th>
        </tr>
        <c:forEach items="${humans}" var="human">
            <tr>
                <td>${human.getWorkName()}</td>
                <td>${human.getWorkDate()}</td>
                <td>${human.getWorkWeek()}</td>
                <td>${human.getWorkLocation()}</td>
                <td>${human.getWorkSubstance()}</td>
                <td>${human.getWorkDuration()}</td>
                <td style="text-align: center;">
                    <a href="updateHum.jsp?name=${human.getWorkName()}&id=${human.getId()}">修改</a>
                    <a href="deleteServlet?id=${human.getId()}">删除</a>
                </td>
            </tr>
        </c:forEach>

    <tr>
        <td colspan="8" align="center">
            已通过审核工作时长：${successExemineDuration}小时。<br>
            未通过审核工时时长：${unExemineDuration}小时。
        </td>
    </tr>
</table>
    <a href="QueryInfo.jsp" style="text-align: center">
        <button>返回主页面</button>
    </a>
</html>
