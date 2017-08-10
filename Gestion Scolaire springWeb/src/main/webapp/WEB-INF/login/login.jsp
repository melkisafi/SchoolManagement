<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<title>Gestionaire scolaire</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<br><br>
					 <div class="page-header">
					 <c:choose>
					 	<c:when test="${userid != null }"> <h1>Logout</h1> </c:when>
					 	<c:otherwise><h1>Login</h1></c:otherwise>
					 </c:choose>
					</div>
					
					<div class="jumbotron">
					
						<c:choose>
							<c:when test="${userid == null }">
								<form:form modelAttribute="login" action="signin" method="post" cssClass="form-horizontal">
								  <div class="form-group">
								    <label for="username" class="col-sm-2 control-label">Username</label>
								    <div class="col-sm-10">
								      <input type="text" name="username" class="form-control" id="username" placeholder="Username">
								    </div>
								  </div>
								  <div class="form-group">
								    <label for="password" class="col-sm-2 control-label">Password</label>
								    <div class="col-sm-10">
								      <input type="password" name="password" class="form-control" id="password" placeholder="Password">
								    </div>
								  </div>
								  
								  <div class="form-group">
								    <div class="col-sm-offset-2 col-sm-10">
								      <button type="submit" class="btn btn-success">Sign in</button>
								    </div>
								  </div>
								</form:form>
							</c:when>
							<c:otherwise>
								<a href="./logout" class="btn btn-danger">Logout</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	</body>
</html>