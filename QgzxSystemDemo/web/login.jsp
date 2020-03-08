<%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/10/11
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
        $("#name").blur(function () {

            //获取id=username的文本输入值
            var name = $("#name").val();
            if (name == ""){
                $("#name_message").html("不能为空").css("color","red");
            }else {
                $("#name_message").html("正确").css("color","green");
            }
        });
    </script>
    <title>Title</title>
</head>
<body>
<%

    // 获取浏览器发送过来的cookie, 获取用户信息
    Cookie[] cookies = request.getCookies();
    String username = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
            }
        }
    }
%>

<font color="red">${requestScope.statusMess}</font>
<font color="red">${requestScope.error}</font>
<form action="verify" method="post">
    账号：<input type="text" name="name" id="name"><label id="name_message"></label> <br>
    密码：<input type="password" name="password"><br>
    验证码:<input type="text" name="image">
    <img src="VerifyCodeServlet">
    <input type="button" value="看不清? 换一张." id="btn"><font color="red">${requestScope.imageMess}</font>
    <br>
    <div>身份：
        <label><input type="radio" name="status" value="admin">管理员</label>
        <label><input type="radio" name="status" value="user">学生</label>
    </div>
    <button type="submit">登陆</button>
</form>
    <a href="register.jsp"><button>注册</button></a>


<script type="text/javascript">

    document.getElementById("btn").onclick = function () {
        // 获取img元素
        // 为了让浏览器发送请求到servlet, 所以一定要改变src
        document.getElementsByTagName("img")[0].src =
            "VerifyCodeServlet?time=" + new Date().getTime();
    };
</script>

</body>
</html>
