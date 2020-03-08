<%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/11/27
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <%--    <script src="js/jquery-3.3.1.min.js"></script>--%>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn").click(function(){
                // alert("hello btn");
                // 发送ajax请求
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"user/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"hehe","password":"123"}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        // data服务器端响应的json的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                    }
                });

            });
        });

    </script>




</head>
<body>
<button id="btn">ajax</button>
<a href="account/findAll">查询所有</a>

<form action="account/save" method="post">
    姓名：<input type="text" name="name" /><br/>
    密码：<input type="text" name="money" /><br/>
    <input type="submit" value="保存"/><br/>
</form>
<form action="user/save" method="post">
    姓名：<input type="text" name="username" /><br/>
    密码：<input type="text" name="password" /><br/>
    <input type="submit" value="保存"/><br/>
</form>

登陆
<form action="user/verify" method="post">
    姓名：<input type="text" name="username" /><br/>
    密码：<input type="password" name="password" /><br/>
    <input type="submit" value="登陆"/><br/>
</form>
</body>
</html>
