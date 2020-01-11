<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>

<html>
<head>
<title>Results</title>
</head>

<body>
	<div id="header">
		<h2>
			Results as of
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
				<th>Test Id</th>
				<th>Wholesale Cost</th>
				<th>Profit Margin</th>
				<th>Yield</th>
				<th>Ep Weight</th>
				<th>Ep Cost</th>
				<th>Ep Retail</th>
				<th>Value of Usable Product</th>
				<th>Cost of Usable Product</th>
			</tr>

			<c:forEach var="tempResult" items="${Results_List}">

				<!--Set up link for each student -->

				<c:url var="tempLink" value="ResultServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="ResultId" value="${tempResult.id}" />

				</c:url>

				

				<tr>
					<td>${tempResult.TestId }</td>
					<td>${tempResult.WholesaleCost }</td>
					<td>${tempResult.profitMargin }</td>
					<td>${tempResult.yield }</td>
					<td>${tempResult.EpWeight }</td>
					<td>${tempResult.EpCost }</td>
					<td>${tempResult.EpRetail }</td>
					<td>${tempResult.ValueofUsableProduct }</td>
					<td>${tempResult.CostofUsableProduct }</td>
					<td><a href="${tempLink}">View</a>
					
				</tr>
				
			</c:forEach>
		</table>
	</div>

</body>

<footer>
	<b>MVS&copy;2019</b>
</footer>
</html>