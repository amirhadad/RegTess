<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link href="css/diff.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="diff-topbar">
		<table class="diffpage-html-firstlast">
			<tbody>
				<tr>
					<td style="text-align: center; font-size: 140%;"><a
						class="diffpage-html-a" href="http://www.cordelta.com/"><img
							class="h1" src="images/Cordelta.png" title="Cordelta website"></a><br>Automated
							regression test<br> Page looks compare service. 
					<br></td>
				</tr>
			</tbody>
		</table>
	</div>
	<form action="HTMLComparerServlet" method="get">
		<table>
			<tr>
				<td>Old HTML page</td>
				<td><input type="text" name="oldhtml" value="http://" /></td>
			</tr>
			<tr>
				<td>New HTML page</td>
				<td><input type="text" name="newhtml" value="http://"/></td>
			</tr>
			<tr>
				<td>Report file name</td>
				<td><input type="text" name="report" value="DefaultReport.html"/></td>
			</tr>
		</table>
		<input type="submit" value="Compare" />
	</form>

</body>
</html>