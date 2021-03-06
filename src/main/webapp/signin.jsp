<%--
  Created by IntelliJ IDEA.
  User: lds
  Date: 2017/10/30
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html lang="zh-CN">
<%
    String ret = request.getParameter("ret");
    if(StringUtils.isNotBlank(ret)) {
        ret = URLEncoder.encode(ret);
    } else {
        ret = "";
    }
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/favicon.ico">

    <title>登陆</title>



    <script src="js/jquery-1.8.2.min.js"></script>
    <script src="js/supersized.3.2.7.min.js"></script>
    <script src="js/supersized-init.js"></script>
    <script src="js/scripts.js"></script>



    <!-- Bootstrap core CSS -->
    <link href="bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/reset.css" rel="stylesheet">
    <link href="css/supersized.css" rel="stylesheet">
    <link href="css/signinstyle.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <%--<link href="css/signin.css" rel="stylesheet">--%>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
    <form class="form-signin" action="/login.page?ret=<%=ret%>" method="post">
        <h1 class="form-signin-heading">请登陆</h1>
        <label for="inputEmail" class="sr-only">邮箱/电话</label>
        <input type="text" id="inputEmail" class="form-control" placeholder="Email/Telephone"  name="username" required autofocus value="${username}">
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" >
        <label for="inputVerifyCode" class="sr-only">验证码</label>
        <input type="text" class="form-control" name="kaptcha" id="inputVerifyCode" placeholder="VerifyCode"  value="">
        <br><img src="kaptcha.jpg" id="kaptchaImage" class="form-group" width="303" height="37" style="border-radius: 7px">
        <small><a href="#" onclick="javascript:document.getElementById('kaptchaImage').src='kaptcha.jpg?' + Math.floor(Math.random() * 100);">
            看不清，点击换一张</a></small>
        <div class="checkbox" style="color: red;">${error}</div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登 陆</button>
    </form>
</div>

<script type="text/javascript">
    $(function() {
        $('#kaptchaImage').click(function() {$(this).attr('src','kaptcha.jpg?' + Math.floor(Math.random() * 100));});

    });
</script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
