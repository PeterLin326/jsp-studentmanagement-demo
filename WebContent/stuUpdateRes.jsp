<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Student"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>수정 성공</title>

</head>

<body> 
<center>
	<br>

	 <h3>수정된 정보：</h3>
    
    <hr>
    <br>
    <br>
<table border="1">
    <tr>
    <td>학번</td>
    <td>비밀번호</td>
    <td>이름</td>
    <td>나이</td>
    <td>성별</td>
    <td>전공</td>
    </tr>
    <%
     response.setCharacterEncoding("UTF-8");
     request.setCharacterEncoding("UTF-8");
     ArrayList<Student> list = new ArrayList<Student>();
		list = (ArrayList<Student>) request.getAttribute("result");
   	    if (list != null) {
		for (Student s : list) {
			out.print("<tr>");
			out.print("<td>" + s.getId() + "</td>");
			out.print("<td>" + s.getPassword() + "</td>");
			out.print("<td>" + s.getName() + "</td>");
			out.print("<td>" + s.getAge() + "</td>");
			out.print("<td>" + s.getGender() + "</td>");
			out.print("<td>" + s.getMajor() + "</td>");
			out.print("</tr>");
		}
       }
     %>
          </table>
      <br>


		<a href="exit.jsp">나가기</a>
	
	</center>
</body>
</html>
