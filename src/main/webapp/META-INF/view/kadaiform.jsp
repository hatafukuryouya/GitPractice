<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Kadai16" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ろぐいんだよ</title>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			Kadai16 ka = (Kadai16)session.getAttribute("input_data");
	%>
		<p style="color:red">登録に失敗しました。</p>
		<h3>新規会員登録</h3>
		<form action="KadairegisterConServlet" method="post">
		名前：<input type="text" name="name" value="<%=ka.getName()%>"><br>
		年齢：<input type="text" name="age"value="<%=ka.getAge()%>"><br>
		男：<input type="radio" name="gender" value="<%=ka.getGender()%>">
		女：<input type="radio" name="gender" value="<%=ka.getGender()%>"><br>
		電話番号:<input type="text" name="tel"value="<%=ka.getTel()%>"><br>
		メール：<input type="email" name="mail" value="<%=ka.getMail()%>"><br>
		パスワード：<input type="password" name="pw"><br>
		<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
	<form action="KadairegisterConServlet" method="post">
		名前：<input type="text" name="name"><br>
		年齢：<input type="text" name="age"><br>
		男：<input type="radio" name="gender" value="男">
		女：<input type="radio" name="gender" value="女"><br>
		電話番号:<input type="text" name="tel"><br>
		メール：<input type="email" name="mail"><br>
		パスワード：<input type="password" name="pw"><br>
		<input type="submit" value="登録">
	</form>
	<%
		}
	%>
</body>
</html>