<%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/11/27
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>ok</title>
</head>
<body>
list.jsp

<h3>查询所有的帐户</h3>

<c:forEach items="${list}" var="account">
    ${account.name}
</c:forEach>

</body>
</html>
