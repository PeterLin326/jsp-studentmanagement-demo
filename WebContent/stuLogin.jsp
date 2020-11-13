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
		<form action="stuLoginServlet" method="post">
			<%
				Object msg1 = request.getAttribute("message1");
				if (msg1 != null)
					out.println(msg1);
			%>
			
			<div><h3>학생 로그인</h3>
			학번：<input type="text" name="id" value=""></input> 
			<br>
			 <br>
			비번：<input type="text" name="password" value=""></input>
			 <br> 
			 <input type="submit" value="로그인" /></input>
			 
			 <a href="stuRegist.jsp">계정 만들기</a></div>
			 <br>
			 <%Object msg2 = request.getAttribute("message2");
			if (msg2 != null)
				out.println(msg2 ); %>
		</form>
	</center>

</body>
</html>