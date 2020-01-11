<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>

<html>
<head>
<title>Cutters</title>
</head>

<body>
	<div id="header">
		<h2>
			Cutters as of
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
				<th>First Name</th>
				<th>Last Name</th>
				<th>Number of Years</th>
				<th>Action</th>
			</tr>

			<c:forEach var="tempTest" items="${Tests_List}">

				<!--Set up link for each student -->

				<c:url var="tempLink" value="TestServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="TestId" value="${tempTest.id}" />
				
					<!--Set up link for delete -->
				
				<c:url var="deleteLink" value="PrimalServlet" >
				<c:param name="command" value="DELETE" />
				<c:param name="PrimalId" value="${tempPrimal.id}" />
				
				</c:url>
				
				</c:url>

				

				<tr>
					<td>${tempTest.FirstName }</td>
					<td>${tempTest.LastName }</td>
					<td>${tempTest.numOfYears }</td>
					<td><a href="${tempLink}">View</a>
					<td><a href="${deleteLink}">Delete</a>
				</tr>
				
			</c:forEach>
		</table>
	</div>

</body>

<footer>
	<b>MVS&copy;2019</b>
</footer>
</html>