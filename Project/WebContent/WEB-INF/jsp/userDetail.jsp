<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ情報詳細参照</title>
</head>
<body>
<p style="background-color:gray;text-align:right"><b><font color=white>${userInfo.name}さん　　　</font></b> <a href="LogoutServlet" class="navbar-link logout-link">ログアウト</a></p>
<h1 style="text-align:center">ユーザ情報詳細参照</h1>
<br>
<p><b>ログインID　　　　</b>${user.loginId} </p>
<p><b>ユーザ名　　　　　</b>${user.name} </p>
<p><b>生年月日　　　　　</b>${user.birthDate} </p>
<p><b>登録日時　　　　　</b>${user.createDate} </p>
<p><b>更新日時　　　　　</b>${user.updateDate} </p>
<br>
<p style="text-align:left"><a href="UserListServlet">戻る</a></p>
</body>
</html>