<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<Title>Cutting Test List</Title>
</head>
<hr/>

<p>The results of all of the cutting test taken are listed here.</p>
<body>
<div id="main"></div>

<div id="results"></div>
<table>
<tr>
<th>ID</th>
<th>PrimalID</th>
<th>TestID</th>
<th>Wholesale Cost</th>
<th>Profit Margin</th>
<th>Yield</th>
<th>Edible Product Weight</th>
<th>Edible Product Cost</th>
<th>Edible Product Retail</th>
<th>Value of Usable Product</th>
<th>Cost of Usable Product</th>
</tr>

<c:forEach var="tempCuttingTest" items="${cutTestList}">
<!-- update link -->
<c:url var="tempLink" value="TestServlet">
<c:param name="command" value="UPDATE"/>
<c:param name="cuttingTestId" value="${tempCutTest}"/>
</c:url>
<!--set up delete link for each cutting test -->

<c:url var="deleteLink" value="TestServlet">
<c:param name="command" value="DELETE"/>
<c:param name="cuttingTestId" value="${tempCutTest}"/>
</c:url>

<tr>

<td>${tempCutTest.primalId }</td>
<td>${tempCutTest.testId }</td>
<td>${tempCutTest.wholeSaleCost }</td>
<td>${tempCutTest.profitMargin }</td>
<td>${tempCutTest.yeild }</td>
<td>${tempCutTest.epWeight }</td>
<td>${tempCutTest.epCost }</td>
<td>${tempCutTest.epRetail }</td>
<td>${tempCutTest.vop }</td>
<td>${tempCutTest.cup }</td>
<td><a href="${tempLink}">Update</a>
<td>
<a href="${deleteLink}"
onclick="if(!(confirm('Are you sure you want to delete this student?')))return false">Delete</a>
</td>

</tr>
</c:forEach>
</table>



<footer></footer>
Copyright MVS INC. 2019
</body>
</html>