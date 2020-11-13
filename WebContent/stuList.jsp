<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Student"%>
<%@ page import="util.Page"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>

<title>학생정보</title>

<script type="text/javascript">
	function confirmF() {
		if (window.confirm("정보를 삭제하시겠습니까?")) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>
	<center>
		<br>
		<h1>학생정보</h1>
		<br> <br>
		<table width="510" border="100"
			style="border: 1pt solid; font-size: 13pt; cellspace: 1pt"
			height="31">
			<tr>
				<td>학번</td>
				<td>이름</td>
				<td>나이</td>
				<td>성별</td>
				<td>전공</td>
			</tr>
			<%
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				Page pager = (Page) request.getAttribute("pager");
				List<Student> list = (List<Student>) request.getAttribute("subResult");
				if (list != null) {
					for (Student s : list) {
						out.print("<tr>");
						out.print("<td>" + s.getId() + "</td>");
						out.print("<td>" + s.getName() + "</td>");
						out.print("<td>" + s.getAge() + "</td>");
						out.print("<td>" + s.getGender() + "</td>");
						out.print("<td>" + s.getMajor() + "</td>");
			%>
			<td><a href="AllServlet?id=<%=s.getId()%>&methodName=<%=2%>" οnclick="return confirmF()">삭제</a></td>
			<%
				out.print("</tr>");
					}
				}
			%>
		</table>
		<span><font size="2.5"> 총<%=pager.getTotalRecord()%>기록,페이지당<%=pager.getPageSize()%>기록
				<br> 페이지<%=pager.getCurrentPage() + 1%>/<%=pager.getTotalPage()%><br>
				<%
					int last = pager.getCurrentRecord() - pager.getPageSize();
					int next = pager.getCurrentRecord() + pager.getPageSize();
					int currentRecord;
					if (last >= 0) {
						out.print("<a href='AllServlet?currentRecord=" + last + "&methodName=1'>이전</a>");
					}
					if (next < pager.getTotalRecord()) {
						out.print("<a href='AllServlet?currentRecord=" + next + "&methodName=1'>다음</a>");
					}
				%>
		</font> </span> <br> <br> <br> <br> <br> <br> <a
			href="adminSearch.jsp">학생 조회</a>
	</center>
</body>
</html>