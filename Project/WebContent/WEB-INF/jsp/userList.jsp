<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ一覧</title>
<style>
table, td, th {border: 1px black solid;}
table {
  width: 400;
  
  margin: 0 auto;
  border: solid 1px;
}
</style>
</head>
<body>
<p style="background-color:gray;text-align:right"><b><font color=white>ユーザ名さん　　　</font></b><a href="login.html" >ログアウト</a></p>
<h1 style="text-align:center">ユーザ一覧</h1>
<br>
<br>
<p style="text-align:right"><a href="register.html" >新規登録</a></p>
<p style="text-align:center">ログインID　　<input type="text"  id="id">

<p style="text-align:center">ユーザ名　　　<input type="text" name="username"></p>
<p style="text-align:center">生年月日　　　<input type="text"  name="birthday"></p>
<p style="text-align:right"><a href="userlist.html"><input type="submit" value="　　検索　　"></a></p>
<hr>

<table>
<tr bgcolor="silver" align="center" >
<th>ログインID</th><th>　ユーザ名　</th><th>生年月日</th><th>　　</th>
</tr>
<tr>
<td>id0001</td>
<td>田中太郎</td>
<td>1989年04月26日</td>
<td>　
<a href="user1.html"><input type="submit" value="詳細"></a>　
<a href="update.html"><input type="submit" value="更新"></a>　
<a href="userdelete.html"><input type="submit" value="削除"></a>　</td>
</tr>
<tr>
<td>id0002</td>
<td>佐藤二郎</td>
<td>2001年11月12日</td>
<td>　
<a href="user1.html"><input type="submit" value="詳細"></a>　
<a href="update.html"><input type="submit" value="更新"></a>　
<a href="userdelete.html"><input type="submit" value="削除"></a>　</td>
</tr>
<tr>
<td>id0003</td>
<td>佐川真司</td>
<td>2000年01月01日</td>
<td>　
<a href="user1.html"><input type="submit" value="詳細"></a>　
<a href="update.html"><input type="submit" value="更新"></a>　
<a href="userdelete.html"><input type="submit" value="削除"></a>　</td>
</tr>
</table>


</body>
</html>