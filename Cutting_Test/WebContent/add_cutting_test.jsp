<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Cutting Test</title>
</head>

<h2>Cutting Test</h2>

<body>

<div id="main">
Primal:

<br/>
<br/>

Weight: 

<br/>
<br/>

Trim: 

<br/>
<br/>

Waste: 
</div>

<div>

<form action="CuttingTestServlet" method="GET">

<input type="hidden" name="command" value="ADD"/>

<table>
<tr>
<td>
<label>Primal</label>

<select name="Primal">
<c:forEach var="tempPrimal" items="${pNameList}"><!-- need to add pNameList to primal and and primalDbUtil -->
<option>tempPrimal</option>

</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
<label>Weight</label>
<input type="text" name="weight"/>
</td>
</tr>

<tr>
<td>
<label>Trim</label>
<input type="text" name="weight"/>
</td>
</tr>

<tr>
<td>
<label>Waste</label>
<input type="text" name="waste"/>
</td>
</tr>

<tr>
<td>
<label></label>
<input type="submit" value="Save" class="save"/>
</td>
</tr>
</table>

</form>
</div>
<br/>
<br/>
<div id="return">
	<p>
	<a href="StudentControllerServlet">Back to List</a>
	</p>
	
</div>
</body>

<footer>Copyright MVS INC. 2019</footer>
</html>