<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Student"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>학생정보 수정하기</title>

</head>

<body>
	<center> 
		<br>
		<h2>학생정보</h2>
		<br>
		<h3>수정할 학생정보</h3>
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
				int id = 0;
				String major = null;
				ArrayList<Student> list = new ArrayList<Student>();
				list = (ArrayList<Student>) request.getAttribute("result");
				if (list != null) {
					for (Student s : list) {
						id = s.getId();
						major = s.getMajor();
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
		<h3>수정할 정보：</h3>
		<form action="stuEditServlet" method="post">
			<table border="1">
			<tr>

				<td>학번：</td>
				<td><%=id %> <font color="red">학변을 수정하면 안됩니다.</font></td>
			<tr>
				<td>비밀번호:</td>
				<td><input type="text" name="password"></input></td>
			</tr>
			<tr>
				<td>이름：</td>
				<td><input type="text" name="name"></input></td>
			</tr>
			<tr>
				<td>나이：</td>
				<td><input type="text" name="age"></input></td>
			</tr>


			<tr>
				<td>성별：</td>
				<td><input type="radio" name="gender" value="남">남 <input
					type="radio" name="gender" value="녀">녀</td>
			</tr>

			<tr>
				<td>전공：</td>
				<td><%=major %> <font color="red">전공을 수정하면 안됩니다.</font></td>
			</tr>

			</table>
			
            <input type="submit" value="수정"/>
			</form>
	</center>
</body>
</html>