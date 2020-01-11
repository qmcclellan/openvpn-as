<!DOCTYPE html>
<html>
<head>
<title>Update Test</title>

</head>
<body>

<div id="wrapper">
		<div id="header">

			<h2>Test Information</h2>

		</div>
	</div>
	<div id="container">
		<div id="content">

		</div>
	</div>
	
	<div id="container">
	<h3>Update Test</h3>
	
	<form action="TestServlet" method="GET">
	
	<input type="hidden" name="command" value="UPDATE" />
	
	<input type="hidden" name="TesttId" value="${Test.id }"/>
	
	<table>
	<tbody>
	<tr>
	<td><label>Cutter:</label>
	<td><input type="text" name="email" 
	value="${THE_TEST.cutterName}"/></td>
	</tr>
	
	<tr>
	<td><label>Primal:</label>
	<td><input type="text" name="firstName" 
	value="${THE_TEST.primalName}"/></td>
	</tr>
	
	<tr>
	<td><label>Weight:</label>
	<td><input type="text" name="lastName" 
	value="${THE_TEST.weight}"/></td>
	</tr>
	
	<tr>
	<td><label>Trim:</label>
	<td><input type="text" name="email" 
	value="${THE_TEST.trim}"/></td>
	</tr>
	
	<tr>
	<td><label>Waste:</label>
	<td><input type="text" name="email" 
	value="${THE_TEST.waste}"/></td>
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