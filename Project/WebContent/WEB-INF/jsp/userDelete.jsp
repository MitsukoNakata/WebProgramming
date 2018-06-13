<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報削除確認</title>
</head>
<body>
<p style="background-color:gray;text-align:right"><b><font color=white>${userInfo.name}さん　　　</font></b> <a href="LogoutServlet" class="navbar-link logout-link">ログアウト</a></p>
<h1 style="text-align:center">ユーザ情報削除確認</h1>
<br>

<p>ログインID:${user3.loginId}を</p>
<p>本当に削除してよろしいでしょうか</p>
<br>
<p style="text-align:center">

<table border="0">
	<tr>
		<td align ="right"><a href="UserListServlet"><input type="submit" value="　　キャンセル　　"></a></td>
		<td>
		<form class="form-signin" action="UserDeleteServlet" method="post">
		<input type="hidden"  name="loginId" value=${user3.loginId} >
		<input type="submit" value="　　　　ＯＫ　　　　"></td></form>

	</tr>





</body>
</html>