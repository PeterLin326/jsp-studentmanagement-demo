<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Student"%>
<%@ page import="java.sql.*"%>
<%@ page import="servlet.stuRegistServlet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
<title>학생정보입력</title>
<script type="text/javascript">
	function validate() { 
		var id = document.forms[0].id.value;
		var name = document.forms[0].name.value;
		var age = document.forms[0].age.value;
		var major = document.forms[0].major.value;
		if (id <= 0 || !parseInt(id)) {
			alert("학번입력하십시오!");
			return false;
		} 
		else if(password.length<=0){
			alert("비밀번호입력하십시오!")
			return false;
		}
		else if (name.length <= 0) {
			alert("이름이력하십시오!");
			return false;
		}

		else if (age <= 0 || !parseInt(age)) {
			alert("나이를 입력하십시오!");
			return false;
		}

		else if (major.length <= 0) {
			alert("전공입력하십시오!");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body>
	<br>
	<center>
		<h2>학생정보입력</h2>
		<form action="stuRegisterServlet" method="post"  id="form" onSubmit="return validate()">
			<table border="1">
				<tr>

					<td>학번：</td>
					<td><input type="text" name="id"></input></td>
					<%
						Object msg = request.getAttribute("message");
						if (msg != null)
							out.println(("<td>") + msg + ("</td>"));
					%>
				</tr>
				<tr>
					<td>비밀번호:</td>
					<td><input type="password" name="password"></input></td>
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
					<td><input type="text" name="major"></input></td>
				</tr>

			</table>
			<input type="submit" value="제출하기" />

		</form>
		<br>
		<h3>
			계정 있습니다.<a href="stuLogin.jsp">로그인하십시오 </a>
		</h3>
	</center>
</body>
</html>
