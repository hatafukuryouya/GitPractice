<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Kadai16" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧だよ</title>
</head>
<style>
<body>
body{
    background: #e6e6fa;
    }
#example{
        margin: auto;
        width: 100%;
  border-collapse: collapse;
        }
}
table th{
  color: #000080;
}
td {
	color:#000080
}
</style>
</head>
<body>
	
	<table id="example" border="1">
		<tr>
			<th>名前</th>
			<th>年齢</th>
			<th>性別</th>
			<th>電話番号</th>
			<th>メールアドレス</th>
		</tr>
	<%
	List<Kadai16> list = (ArrayList<Kadai16>)request.getAttribute("list");
	for(Kadai16 s : list) {
	%>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getAge() %></td>
			<td><%=s.getGender() %></td>
			<td><%=s.getTel() %></td>
			<td><%=s.getMail() %></td>
		</tr>
	<%} %>
</table>
<a href="./">戻る</a>
</body>
</html>