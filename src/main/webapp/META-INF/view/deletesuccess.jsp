<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除成功だよ</title>
</head>
<body>
	<h1 style="color:red">下記のメールアドレスのデータを削除しました。</h1>
	<%
	String mail = request.getParameter("mail");
	%>
	
	<a href="Listservlet">一覧表示へ戻る</a>
</body>
</html>