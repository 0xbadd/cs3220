<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>View Files</title>
	<link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Welcome</h1>
		<form action="Upload" method="post" enctype="multipart/form-data">
			File: <input type="file" name="file" /> <input type="submit"
				name="upload" value="Upload" />
		</form>
		
		<c:if test="${empty files}">
			<p>There are no files yet.</p>
		</c:if>
		
		<c:if test="${not empty files}">
			<c:forEach items="${files}" var="file">
				<div class="card">
					<div class="card-header">
					    <c:out value = "${file.value.filename}"/>
					    <a href="Delete?id=${file.key}" class="float-right">Delete</a>
					</div>
					<div class="card-body">
					    <a href="Download?path=${file.value.filepath}">Download</a>
					    <a href="Rename?id=${file.key}" class="float-right">Rename</a>
					</div>
				</div>
			</c:forEach> 
		</c:if>
	</div>
</body>
</html>