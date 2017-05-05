<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  ~ Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  ~ Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
  ~ Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
  ~ Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
  ~ Vestibulum commodo. Ut rhoncus gravida arcu.
  --%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/xin.css"/>
</head>
<body>
<div id="login">
    <h1>Login</h1>
    <form method="post" action="/login">
        <input type="text" required="required" placeholder="用户名" name="username"/>
        <input type="password" required="required" placeholder="密 码" name="password"/>
        <button class="but" type="submit">登录</button>
    </form>
    <div style="color: #FF0000">${message}</div>
</div>
</body>
</html>