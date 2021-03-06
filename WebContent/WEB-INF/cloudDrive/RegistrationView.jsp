<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Cloud Drive Registration</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
	<div class="page-header my-4 mx-auto" style="width: 40rem;">
		<h1 class="display-4 text-center">New User Registration</h1>
	</div>
	<c:if test="${not empty error}">
	   <div class="card mx-auto my-3" style="width: 30rem;">
		   <div class="card-header text-white bg-danger">
			   An error has occurred.
		   </div>
		   <div class="card-body">
			   <code>
				   <c:if test="${error == 'username'}">
					   There is already an account under this username.
				   </c:if>
				   <c:if test="${error == 'password'}">
					   Passwords do not match.
				   </c:if>
				   <c:if test="${error == 'email'}">
					   There is already an account under this email.
				   </c:if>
				   <c:if test="${error == 'badusername'}">
					   Usernames can not contain special characters or spaces.
				   </c:if>
				   <c:if test="${error == 'bademail'}">
					   Not a valid email address.
				   </c:if>
				   <c:if test="${error == 'emptyfields'}">
					   All fields must be filled in.
				   </c:if>
			   </code>
		   </div>
	   </div>
	</c:if>
	<div class="row mt-3">
	    <div class="col">
			<form class="mx-auto my-3" style="width: 30rem;" action="Register" method="post">
				<div class="form-group">
					<label for="username">User name</label>
					<input class="form-control" id="username" name="username">
				</div>
				<div class="form-group">
					<label for="email">E-mail</label>
					<input class="form-control" id="email" name="email">
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<input class="form-control" type="password" id="password" name="password">
				</div>
				<div class="form-group">
					<label for="password2">Repeat password</label>
					<input class="form-control" type="password" id="passwordRepeat" name="passwordRepeat">
				</div>
				<input class="btn btn-primary" type="submit" name="submitBtn" value="Register">
			</form>
		</div>
	</div>
	<hr class="my-4" style="width: 40rem;">
	<div class="row mt-3">
	    <div class="col">
	        <p class="mx-auto" style="width: 30rem;">
	            Already have an account?
	            <a href="Login">Login</a>
	        </p>
	    </div>
	</div>
</div>
</body>
</html>