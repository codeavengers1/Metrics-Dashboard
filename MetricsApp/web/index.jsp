<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" media="screen" href="js/jquery/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jquery/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/jqGrid/css/ui.jqgrid.css" />
<script type="text/javascript" src="js/matricsDashboard.js"></script>
<script src="js/jquery/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery-ui.min.js" type="text/javascript"></script>
<script src="js/jquery/jquery.layout-latest.js" type="text/javascript"></script>
<script src="js/jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" media="screen" href="js/FlexBox/css/jquery.flexbox.css" />
<script src="js/FlexBox/js/jquery.flexbox.min.js" type="text/javascript"></script>

<script type="text/javascript" src="js/flot/jquery.flot.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.axislabels.js"></script>
<script type="text/javascript" src="js/flot/jquery.flot.time.js"></script>
<title>Log Analysis</title>

<style type="text/css">
#placeholder { width: 550px; height: 300px; }
.legend table, .legend > div { height: 50px !important; opacity: 1 !important; right: 285px; top: 10px; width: 100px !important; }
.legend table { border: 1px solid #555; padding: 5px; }
</style>

</head>
<body>

<form action="FirstServlet" method="get">
<h2 align="center">Analysis Tool</h2>
<fieldset><legend>Search</legend>
	<table>
		<tr>
			<td><span style="color:red">*</span>Title:</td>
			<td><input type="text" id="title" name="title" size="30" value="Rec1"/></td>
			<td rowspan="4" style="width:30px;"></td>
			<td>Filter:</td>
			<td><input type="text" id="filter" name="filter" size="30" value="Keyword"/></td>
			<td></td>
		</tr>
		<tr>
			<td><span style="color:red">*</span>WR#:</td>
			<td><div id="wrName"></div></td>
			<td></td>
			<td colspan="1" align="center">
				<input type="button" value="Add Filter &nbsp;" onclick="javascript:md.addKeyword();"/>
				<input type="button" value="Remove Filter  " onclick="javascript:md.removeKeyword();"/>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>LogType:</td>
			<td><div id="logType"></div></td>
			<td rowspan="2"><span style="color:red">*</span>Selected <br> Filters:
			</td>
			<td rowspan="2" ><select id="selectedFilter" style="width:203px;" size=3></select></td>
			<td></td>
		</tr>
			
		<tr>
			<td>Date:</td>
			<td ><input type="text" id="date" name="date" size="30" value="27/09/1991"/></td>
			<td align="right" style="width:80px;"><input type="button" value="Process" onclick="javascript:md.process();"/></td>
		</tr>
	</table>
</fieldset>
<br/>

<div id="responseTxt" style="display:none"></div>
<br/>
<table>
	<tr>
		<td><div id="matricsGridDiv"><table id="matricsGrid"></table></div></td>
		<td><div id="placeholder"></div> </td>
	</tr>
</table>

</form>

</body>
<script>
	$(document).ready(function () {
	//	md.loadOnReady();
	});
	md.loadFlexBoxes();
	
</script>
</html>