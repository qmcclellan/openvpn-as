<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>

<html>
<head>
<title>Test List</title>
</head>

<body>
	<div id="header">
		<h2>
			Tests as of
			<%
			new java.util.Date();
		%>
		</h2>
	</div>
	<hr />
	<div id="Add Button"></div>
	<div id="main"></div>

	<div id="table">
		<table>

			<tr>
				<th>Cutter Name</th>
				<th>Primal Name</th>
				<th>Weight</th>
				<th>Trim</th>
				<th>Waste</th>
			</tr>

			<c:forEach var="tempTest" items="${Tests_List}">

				<!--Set up link for each student -->

				<c:url var="tempLink" value="TestServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="TestId" value="${tempTest.id}" />

				</c:url>

				

				<tr>
					<td>Cutter Name</td>
				<td>Primal Name</td>
				<td>Weight</td>
				<td>Trim</td>
				<td>Waste</td>
			</tr>
					<td><a href="${tempLink}">View</a>
					
				
				
			</c:forEach>
		</table>
	</div>

</body>

<footer>
	<b>MVS&copy;2019</b>
</footer>
</html>