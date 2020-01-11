<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<title>List of Primals</title>
</head>

<body>
<div id="header">
<h2>Current Primal List As of: <% new java.util.Date(); %></h2>
</div>

<div id="main">

</div>

<div id="table">

<table>
<tr>
<th>Name</th>
<th>Category</th>
<th>Cost Per Pound </th>
<th>Retail Per Pound</th>
<th>Action</th>

</tr>


<!-- Add for each  -->

<c:forEach var="tempPrimal" items="${Primal_List }">

	
				<c:url var="tempLink" value="PrimalServlet" >
				<c:param name="command" value="LOAD" />
				<c:param name="PrimalId" value="${tempPrimal.id}" />
				
				</c:url>
				
				<!--Set up link for delete -->
				
				<c:url var="deleteLink" value="PrimalServlet" >
				<c:param name="command" value="DELETE" />
				<c:param name="PrimalId" value="${tempPrimal.id}" />
				
				</c:url>
				
				<tr>
				
				<td>${tempPrimal.name}</td>
				<td>${tempPrimal.category}</td>
				<td>${tempPrimal.costLb}</td>
				<td>${tempPrimal.RetailLb}</td>
				<td><a href="${tempLink}">Update</a>
							 |
							<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this student?')))return false">Delete</a>
							</td>
						
				</tr>

</c:forEach>


</table>

</div>

</body>

<footer>
MVS&copy;2019
</footer>
</html>