<!DOCTYPE html>
<html>

<head>
<title> </title>
</head>

<body>

<div id ="main">
<h3> </h3>
</div>

<div id="menu">

</div>

<div id="form">
<form action="PrimalServlet" method = "GET">

<input type="hidden" value="command" value="ADD"/>

<table>

<tr>
<td><label>Cutter Name</label></td>
<td><input/></td>
</tr>

<tr>
<td><label>Primal Name</label></td>
<td><input type= "text" value="primalName"/></td>
</tr>

<tr>
<td><label>Weight</label></td>
<td><input type="text" value="weight"/></td>
</tr>

<tr>
<td><label>Trim</label></td>
<td><input type="text" value="trim"/></td>
</tr>

<tr>
<td><label>Waste</label></td>
<td><input type="text" value="waste"/></td>
</tr>

<tr>
<td><label></label></td>
<td><input type="submit" value="save" class ="save"/></td>
</tr>
</table>


</form>

</div>

<footer>

<b>MVS&copy;2019</b>
</footer>


</body>

</html>