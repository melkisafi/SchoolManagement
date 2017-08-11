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
					<div class="page-header">
						<h1>Edition du login</h1>
					</div>
					<div class="alert alert-warning" role="alert">
					  Merci de modifier votre mot de passe.
					</div>
					
					<div class="jumbotron">
						<form:form modelAttribute="login" action="save" method="post" cssClass="form-horizontal">
							 <form:hidden path="id" />
							 <form:hidden path="version" />
							 <input type="hidden" name="mode" value="${mode}">
							 <input type="hidden" name="disabled" value="1">
							 <fieldset disabled>
					
							   <div class="form-group">
							    	<form:label path="username">Username</form:label>
									<form:input path="username" type="text" cssClass="form-control" value="${login.username}" />				
							   </div>
							   </fieldset>
							   <div class="form-group">
							   <form:label path="password">Password</form:label>
							   <form:input path="password" type="password" cssClass="form-control" value="${login.password}"/>
						    	</div>
						  
						    	<button type="submit" class="btn btn-warning">Modifier</button>
						
						</form:form>
					</div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</body>
</html>