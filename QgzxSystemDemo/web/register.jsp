<%--
  Created by IntelliJ IDEA.
  User: 17566
  Date: 2019/10/9
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<%--    <script src="js/jquery-3.3.1.min.js"></script>--%>

    <script>

        //在页面加载完成后
        $(function () {
            //添加失焦事件
            //给username绑定blur事件
            $("#username").blur(function () {
                //获取id=username的文本输入值
                var username = $("#username").val();
                //发送ajax请求
                //期望服务器响应回的数据格式：{"userExist":true,"msg":"此用户名太受欢迎,请更换一个"}
                //                         {"userExist":false,"msg":"用户名可用"}


                $.ajax({
                    url:'findHumanNameServlet',
                    type:'POST',
                    data: {'username':username},
                    dataType:'json',
                    success:function(result){
                        var span = $("#s_username");
                        if (result.Exist){
                            //为真，已经存在
                            span.css("color","red");
                            span.html(result.msg);
                        }else {
                            //用户名不存在
                            span.css("color","green");
                            span.html(result.msg);
                        }
                    },
                    error:function(){
                        alert("a")
                    }
                });

            });
        });
    </script>




</head>
<body>
<form action="registerServlet" method="post">

    用户名：<input type="text" id="username" name="username" placeholder="请输入用户名">
    <span id="s_username"></span>
    <br>


    <input type="password" name="password" placeholder="请输入密码"><br>
    验证码:<input type="text" name="image">
    <img src="VerifyCodeServlet">
    <input type="button" value="看不清? 换一张." id="btn"><font color="red">${requestScope.imageMess}</font>
    <br>
    <input type="submit" value="注册"><br>

</form>

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
