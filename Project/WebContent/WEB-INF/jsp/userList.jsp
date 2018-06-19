<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
<style>
table, td, th {border: 0px black solid;}
table {
  width: 500;
  margin: 0 auto;
  border: solid 0px;
}
</style>
</head>
<body>
<p style="background-color:gray;text-align:right"><b><font color=white>${userInfo.name}さん　　　</font></b> <a href="LogoutServlet" class="navbar-link logout-link">ログアウト</a></p>
<h1 style="text-align:center">ユーザ管理システム</h1>
<br>
<br>
 <div class="panel-body">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title" align="center">検索条件</div>

				<c:if test ="${userInfo.id == 1}">
				<p style="text-align:right"><a href="RegisterServlet" >新規登録</a></p>
				</c:if>
	<form class="form-signin" action="UserListServlet" method="post">
	<table border="0">
		<tr>
		<td>
		<td  align="center">
		<c:if test="${errMsg!= null}" >
		 <font color="red">${errMsg}</font>
		</c:if>
		<tr>
			<td align="right"><b>ログインID　</b></td>
			<td><input type="text"  name="loginId" size="39" maxlength="20" value=${loginId}></td>

		</tr>
		<tr>
			<td align="right"><b>ユーザ名　</b></td>
			<td><input type="text"  name="name" size="39" maxlength="20" value=${name}></td>
		</tr>
		<tr>
			<td align="right"><b>生年月日　</b></td>
			<td><input type="date" name="date-start" id="date-start" value=${dateStart} class="form-control" size="30"/>~
					<input type="date" name="date-end" id="date-end" value=${dateEnd} class="form-control"/></td>
		</tr>
		<tr>
			<td></td>
			<td align = "right"><input type="submit" value="検索"></td>
		</tr>
	</table>
	</form>
<br>
<br>
<br>


<table>
<tr bgcolor="silver" align="center" >
<th>ログインID</th><th>　ユーザ名　</th><th>生年月日</th><th>　　</th>
</tr>
  <tbody>
                 <c:forEach var="user" items="${userList}" >
                   <tr>
                     <td>${user.loginId} </td>
                     <td>${user.name}</td>
                   	 <td>${user.birthDate} </td>
                     <!-- TODO 表示制御を行う -->
					 <td>
					<c:choose>
					<c:when test ="${userInfo.id == 1}">
						<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}"><input type="submit" value="詳細"></a>
						<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}"><input type="submit" value="更新"></a>
						<a class="btn btn-danger" href ="UserDeleteServlet?id=${user.id}"><input type="submit" value="削除" ></a>
				     </c:when>
				     <c:when test ="${userInfo.id == user.id}">
						<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}"><input type="submit" value="詳細"></a>
						<a class="btn btn-success" href="UserUpdateServlet?id=${user.id}"><input type="submit" value="更新"></a>

				     </c:when>
				     <c:otherwise>
						<a class="btn btn-primary" href="UserDetailServlet?id=${user.id}"><input type="submit" value="詳細" ></a>

				     </c:otherwise>
					</c:choose>
					</td>
                   </tr>
                 </c:forEach>
               </tbody>
</table>


</body>
</html>