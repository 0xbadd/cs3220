<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>File List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="page-header my-4 mx-auto" style="width: 40rem;">
        <h1 class="display-4 text-center">File List</h1>
    </div>
    <div class="row mt-3">
        <div class="col">
			<form class="my-3 mx-auto text-center" action="Upload" method="post" enctype="multipart/form-data">
				File:
				<input type="file" name="file"/>
				<input type="submit" name="upload" value="Upload"/>
			</form>
			
			<c:if test="${not empty param.id}">
				<form class="my-3 mx-auto text-center" action="Rename" method="post">
				    ${param.name}: 
					<input type="text" name="newName" placeholder="Change name to...">
					<input type="hidden" name="id" value="${param.id}">
					<input type="submit" value="Submit">
				</form>
			</c:if>

		    <hr class="my-4" style="width: 40rem;">

			<c:if test="${empty files}">
			    <div class="my-5 text-center">
				    <h1>There are no files yet.</h1>
			    </div>
			</c:if>

			<c:forEach var="file" items="${files}">
				<div class="card mb-3 mx-auto" style="width: 25rem;">
					<h5 class="card-header text-white bg-primary text-center">
						${file.value.filename}
					</h5>
					<div class="card-body text-center">
						<span class="mx-4">
							<a href="FileList?id=${file.key}&name=${file.value.filename}">
								<i class="fas fa-edit fa-lg float-left" style="color:black"></i>
							</a>
						</span>
						<span class="mx-4">
							<a href="Download?id=${file.key}">
								<i class="fas fa-download fa-lg" style="color:black"></i>
							</a>
						</span>
						<span class="mx-4">
							<a href="Delete?id=${file.key}" style="color:black">
								<i class="fas fa-trash fa-lg float-right"></i>
							</a>
						</span>
					</div>
				</div>
			</c:forEach>
        </div>
    </div>
</div>
</body>
</html>