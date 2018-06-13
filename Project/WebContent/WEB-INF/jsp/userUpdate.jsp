<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報更新</title>
</head>
<body>



<p style="background-color:gray;text-align:right"><b><font color=white>${userInfo.name}さん　　　</font></b> <a href="LogoutServlet" class="navbar-link logout-link">ログアウト</a></p>
<h1 style="text-align:center">ユーザ情報更新</h1>
<br>

<font color="red"><c:if test="${errMsg!= null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if></font>
<form class="form-signin" action="UserUpdateServlet" method="post">
<table border="0">
	<tr>
		<td align="right"><b>ログインID　</b></td>
        <td><input type="hidden"  name="loginId" value=${user2.loginId} >${user2.loginId} </td>
    </tr>
    <tr>
    	 <td align="right"><b>パスワード　</b></td>
		<td><input type="password"  name="password" size="30" maxlength="20"></td>
	</tr>

	<tr>
		<td align="right"><b>パスワード(確認)　</b></td>
		<td><input type="password"  name="password2" size="30" maxlength="20"></td>
	</tr>
	<tr>
		<td align="right"><b>ユーザ名　</b></td>
		<td>	<input type="text" name="name" size="30" maxlength="20" value=${user2.name}></td>
	</tr>
	<tr>
		<td align="right"><b>生年月日　</b></td>
		<td><input type="text"  name="birthDate" size="30" maxlength="20"value=${user2.birthDate}></td>
	</tr>
	<tr>
		<td></td>
		<td align="center"><input type="submit" value="　　更新　　"></td>
	</tr>
</table>
</form>

<p style="text-align:left"><a href="UserListServlet">戻る</a></p>
</body>
</html>