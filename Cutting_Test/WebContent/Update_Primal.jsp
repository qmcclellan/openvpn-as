<!DOCTYPE html>
<html>
<head>
<title>Update Primal</title>

</head>
<body>

<div id="wrapper">
		<div id="header">

			<h2>Primal</h2>

		</div>
	</div>
	<div id="container">
		<div id="content">

		</div>
	</div>
	
	<div id="container">
	<h3>Update Primal</h3>
	
	<form action="PrimalServlet" method="GET">
	
	<input type="hidden" name="command" value="UPDATE" />
	
	<input type="hidden" name="studentId" value="${THE_PRIMAL.id }"/>
	
	<table>
	<tbody>
	<tr>
	<td><label>Primal name:</label>
	<td><input type="text" name="firstName" 
	value="${THE_STUDENT.name}"/></td>
	</tr>
	
	<tr>
	<td><label>Category:</label>
	<td><input type="text" name="category" 
	value="${THE_STUDENT.category}"/></td>
	</tr>
	
	<tr>
	<td><label>Cost Per Pound:</label>
	<td><input type="text" name="costLb" 
	value="${THE_STUDENT.costLb}"/></td>
	</tr>
	
	<tr>
	<td><label>Retail Per Pound:</label>
	<td><input type="text" name="retailLb" 
	value="${THE_STUDENT.retailLb}"/></td>
	</tr>	
	
	<tr>
	<td><label></label>
	<td><input type="submit" value="Save" class="save" /></td>
	</tr>
	</tbody>
	
	
	
	</table>
	
	</form>
	<div style="clear:both;"></div>
	<p>
	<a href="StudentControllerServlet">Back to List</a>
	</p>
	</div>

</body>



</html>