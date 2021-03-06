<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- JSPに使用するJSTLのURUとprefixをtaglibディレクティブで宣言する。 -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>ログイン画面</title>
	</head>
	<body>
	<div class="container">
	<c:if test="${errorMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errorMsg}
		</div>
	</c:if>

	<form class="form-signin" action="LoginServlet" method="post">
	<h1 style="text-align:center">ログイン画面</h1>
	<br>
	<br>
	<p style="text-align:center">ログインID　　<input type="text"  name="loginId">
	<p style="text-align:center">パスワード　　<input type="password" name="password"></p>
	<br>
	<button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">ログイン</button>
	</form>
	</body>
	</html>