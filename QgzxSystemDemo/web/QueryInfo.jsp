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
    <c:if test="${sessionScope.status=='admin'}">
        <a href="HumanStatisticServlet"><button>管理员界面</button></a>
    </c:if>
    <tr align="center">
        <form class="form-inline" action="nameSearch">
            <td colspan="6" class='form-inline'>
                <label>姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" name="nameSearch">
                <button type="submit" class="btn btn-default">查询</button>
            </td>
        </form>
        <td colspan="7" style="text-align: right;">
            <a href="addHum.jsp">添加人员信息</a>
        </td>
    </tr>
    <tr style="height: 40px;">
        <th style="width:10%;text-align: center;">姓名</th>
        <th style="width:10%;text-align: center;">工作时间</th>
        <th style="width:10%;text-align: center;">工作周</th>
        <th style="width:10%;text-align: center;">工作位置</th>
        <th style="width:10%;text-align: center;">工作内容</th>
        <th style="width:10%;text-align: center;">工作时长</th>
        <th style="width:20%;text-align: center;">操作</th>
        <c:if test="${sessionScope.status=='admin'}">
            <th style="width: 10%;text-align: center">状态</th>
        </c:if>
    </tr>

    <c:forEach items="${humans}" var="human">
<%--        管理员进入显示所有审核未审核的信息   --%>
        <c:if test="${sessionScope.status=='admin'}">
                <tr>
                    <td><a href="nameSearch?nameSearch=${human.getWorkName()}" >${human.getWorkName()}</a></td>
                    <td>${human.getWorkDate()}</td>
                    <td>${human.getWorkWeek()}</td>
                    <td>${human.getWorkLocation()}</td>
                    <td>${human.getWorkSubstance()}</td>
                    <td>${human.getWorkDuration()}</td>
                    <td style="text-align: center;">
                        <a href="updateHum.jsp?name=${human.getWorkName()}&id=${human.getId()}">修改</a>
                        <a href="deleteServlet?id=${human.getId()}">删除</a>
                    </td>
                    <td>
        <%--             管理员并且显示其审核状态并操作--%>
                            <c:if test="${human.getExemine()=='0.0'}">
                                <font color="red">未通过</font>
                                <a href="AdminExemineServlet?id=${human.getId()}"><button>确定审核</button></a>
                            </c:if>
                            <c:if test="${human.getExemine()=='1.0'}">
                                <font color="green">已通过</font>
                            </c:if>
                    </td>
                </tr>
        </c:if>
        <c:if test="${sessionScope.status=='user'}">
            <c:if test="${human.getExemine()=='1.0'}">
                <tr>
                    <td><a href="nameSearch?nameSearch=${human.getWorkName()}" >${human.getWorkName()}</a></td>
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
            </c:if>
        </c:if>
    </c:forEach>
    <tr>
        <td colspan="8" align="center">
            <span>当前是第 ${currPage}页</span>
            <c:if test="${start!=1}">
                <a href="mainServlet?start=1">【首页】</a>
                <a href="mainServlet?start=${pre}">【上一页】</a>
            </c:if>
            <c:if test="${start!= last}">
                <a href="mainServlet?start=${next}">【下一页】</a>
                <a href="mainServlet?start=${last}">【末页】</a>
            </c:if>
        </td>
    </tr>
    <tr>
        <td colspan="8" align="center">
            已通过审核工作时长：${successExemineDuration}小时。<br>
            未通过审核工时时长：${unExemineDuration}小时。
        </td>
    </tr>
</table>