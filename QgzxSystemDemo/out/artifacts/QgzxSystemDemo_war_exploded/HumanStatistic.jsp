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
<%@ page contentType="text/html;charset=utf-8" language="java" isErrorPage="true" errorPage="index.jsp" %>

<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>


<head>
    <title>所有人员信息</title>
</head>

<table class="table table-bordered" align='center' style="width: 1000px;margin-top: 20px;text-align: center;">


<a href="login.jsp" style="text-align: center"><button>注销</button></a>
    <tr style="height: 40px;">
        <th style="width:10%;text-align: center;">姓名</th>
        <th style="width:10%;text-align: center;color: green">已审核工作时间</th>
        <th style="width:10%;text-align: center;color: red;">未审核工作时间</th>
        <th style="width:10%;text-align: center;color: red;">操作</th>

    </tr>

    <c:forEach items="${humanStatisticServlet}" var="humanStatistic">
        <tr>
            <td>${humanStatistic.getWorkName()}</td>
            <td>${humanStatistic.getSuccessExemineDuration()}</td>
            <td>${humanStatistic.getUnExemineDuration()}</td>
            <td style="text-align: center;">
                <a href="deleteServlet?name=${humanStatistic.getWorkName()}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>