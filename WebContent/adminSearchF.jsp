<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>찾은 사람이 없습니다ㅠ</title>

</head>

<body>
	<%
		out.print("<center><br><br><h3>조건에 맞은 정보가 없습니다！</h3></center>");
	%>
	<br>
	<br>
	<center>
		<a href="inputStudent.jsp">입력페이지로 돌아가기</a> <a href="AllServlet?methodName=<%=1%>&id=<%=""%> &name=<%=""%>">정보조회페이지로 돌아가기</a>
	</center>
</body>
</html>