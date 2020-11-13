<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>관리자 로그인</h1>
<br>
<br>
<br>
아이디：<input type="text" name="id" value=""></input>
		 <br> 
		 비밀번호<input type="text" name="name" value=""></input>
		 <br>
		   <input type="submit" value="로그인" /></input>
		   <input type="submit" value="만들기"></input>
<a href="AllServlet?methodName=<%=1%>&id=<%=""%>&name=<%=""%>">학생 정보보기</a>

</center>

</body>
</html>