<%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/9/29
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<html>
<style>
    body{
        background-color: bisque;
    }
</style>
<head>
    <title>修改学生信息</title>
</head>
<body>
<form action="updateServlet?id=${param.id}" method="post" >
    ${param.id}
    <center>
        <table class="gridtable">
            <tr>
                <td align="center" colspan="2">修改学生信息</td>
            </tr>
            <tr>
                <td>工作人员：${param.name}</td>
            </tr>
            <tr>
                <td>工作日期：</td><td><input name="date" type="text" ></td>
            </tr>
            <tr>
                <td>工作周：</td><td><input name="week" type="number" step="1" min="1" max="20" ></td>
            </tr>
            <tr>
                <td>工作内容：</td><td><input name="substance" type="text"></td>
            </tr>
            <tr>
                <td>工作位置：</td><td><input name="location" type="text" ></td>
            </tr>
            <tr>
                <td>工作时长：</td><td><input name="duration" type="text" onkeyup="value= value.replace(/[^\d]/g,'')" ></td>
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
