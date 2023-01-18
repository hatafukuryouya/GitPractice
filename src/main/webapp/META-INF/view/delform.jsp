<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除ページだよ</title>
</head>
<body>
<h3>削除します</h3>
	<form action="KadaiDelConServlet" method="post">
		メール：<input type="email" name="mail"><br>
		<input type="submit" value="削除">
	</form>
</body>
</html>