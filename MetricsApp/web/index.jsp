<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/matricsDashboard.js"></script>
<title>matricsDashboard</title>
</head>
<body>

<h1>Demo!</h1>
<form action="FirstServlet" method="get">
<table>
	<tr>
		<td><span style="color:red">*</span>Title:</td>
		<td><input type="text" id="title" name="title" size="40" value="Rec1"/></td>
		<td><span style="color:red">*</span>WR#:</td>
		<td><input type="text" id="wrNum" name="wrNum" size="40" value="TestWR"/></td>
	</tr>
	<tr>
		<td>Filename:</td>
		<td><input type="text" id="fileName" name="fileName" size="40" value="Error_log"/></td>
		<td><span style="color:red">*</span>Filter:</td>
		<td><input type="text" id="filter" name="filter" size="40" value="Keyword"/></td>
	</tr>
	<tr>
		<td>Date:</td>
		<td colspan="3"><input type="text" id="date" name="date" size="40" value="27/09/1991"/></td>
	</tr>
	<tr>
		<td colspan="4" align="right"><input type="button" value="Process" onclick="javascript:md.process();"/></td>
	</tr>
</table>
<br/>

<div id="responseTxt"></div>

</form>

</body>
</html>