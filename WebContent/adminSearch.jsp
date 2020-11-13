<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>학생 정보</title>

</head>

<body>
	<br>
	<h3>학생정보</h3>
	<hr>
	<br>
	<table border="1">
		<form action="AllServlet" method="post">
    <input type="hidden" name="methodName" value="5"/>
      <h3>按学号姓名查询:</h3>
                              学号：<input type="text" name="id"  value="" title="学号必须为数字" ></input>
                              姓名：<input type="text" name="name" value="" title=""></input>
      <input type="submit" value="查询" />
    </form>


	</table>
	<br>
	<br>
	<h4>
		<a href="AllServlet?methodName=<%=1%>&id=<%=""%>&name=<%=""%>">정보조회페이지로
			돌아가기</a>
	</h4>
</body>
</html>