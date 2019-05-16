<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rename File</title>
</head>
<body>
	<form action="Rename" method="post">
		<input type="text" name="newName" placeholder="Change name to">
		<input type="hidden" name="id" value="${param.id}">
		<input type="submit" value="Submit">
	</form>
</body>
</html>