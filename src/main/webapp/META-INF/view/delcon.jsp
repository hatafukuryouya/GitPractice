<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認だよ</title>
</head>
<body>
<p>下記のデータを削除します。よろしいですか？</p>
	<%
		String mail = (String)session.getAttribute("input_data");
	%>
	<h3>削除するメールアドレス：<%=mail %></h3><br>
	<a href="KadaiDelExeServlet">OK</a><br>
	<a href="KadaiDelFormServlet">戻る</a>
</body>
</html>