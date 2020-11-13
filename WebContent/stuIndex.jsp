<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=utf-8">
<title>학생 정보</title>

</head>

<body>
<center>
	<br>
	<%! @SuppressWarnings("unchecked") %>
	<%
		ArrayList<Student> list = new ArrayList<Student>();
		list = (ArrayList<Student>) request.getAttribute("result");
		String name = null;
		if (list != null) {
			for (Student s : list) {
				name = s.getName();
			}
		}
	%>

	<h3>
		안녕하세요
		<%
		out.print(name);
	%>학생
	</h3>
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

			if (list != null) {
				for (Student s : list) {
					out.print("<tr>");
					out.print("<td>" + s.getId() + "</td>");
					out.print("<td>" + s.getPassword() + "</td>");
					out.print("<td>" + s.getName() + "</td>");
					out.print("<td>" + s.getAge() + "</td>");
					out.print("<td>" + s.getGender() + "</td>");
					out.print("<td>" + s.getMajor() + "</td>");
			%>	
			
					<tr>
				     <td><a href="stuUpdateServlet?id=<%=s.getId() %>&&methodName=<%="stuUpdate" %>">수정</a></td>
				       </tr>
		    <% 
		    out.print("</tr>");
		            }
		       }
		 %>
	</table>
	
	<br>
	<h4>
		<a href="exit.jsp">나가기</a>
	</h4>
	</center>
</body>
</html>