<!Doctype html>
<html>
<head>
<title>Cutting test retrieval</title>
</head>

<body>
<div id="main">
<form action="TestServlet" method="GET">
<input type="hidden" name ="command" value="UPDATE"/>
<input type="hidden" name="cuttingTesdtId" value="${cuttingTest.id }"/>

<table>

<tr>
<td><label>Primal</label></td>
<td><input type="text" name="primalName"
	value="${thePrimal.name }"/>
</td>
</tr>

<tr>
<td><label>Weight</label></td>
<td><input type="text" name="weight"
	value="${cuttingTest.weight }"/>
</td>
</tr>

<tr>
<td><label>Trim</label></td>
<td><input type="text" name="trim"
	value="${cuttingTest.trim }"/>
</td>
</tr>

<tr>
<td><label>Waste</label></td>
<td><input type="text" name="waste"
	value="${cuttingTest.waste }"/>
</td>
</tr>

<tr>
<td><label></label></td>
<td><input type=submit name="Save" class= save/>
</td>
</tr>

</table>
</form>
</div>

<p>
<a href="TestServlet">Back to Cutting List</a>
</p>

</body>
</html>