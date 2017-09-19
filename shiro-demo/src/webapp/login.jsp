<%--
  Created by IntelliJ IDEA.
  User: wen
  Date: 9/17/17
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Login Page</title>
</head>
<body>
	<h4>Login Page</h4>

	<form action="shiro/login.do" method="post">

		Username: <input type="text" name="username" id="username">
		<br><br>

		Password: <input type="password" name="password" id="password">
		<br><br>

		<input type="submit" value="Login">

	</form>

</body>
</html>
