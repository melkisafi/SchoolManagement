<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<c:if test="${typeMess != null }">
			<div class="alert alert-${typeMess}" role="alert">${message}</div>
		</c:if>
	</div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header"><h1>Liste des matières</h1></div>
	</div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<table class="table table-striped">
			<tr>
				<th>#</th>
				<th>Nom</th>
				<th>Couleur</th>
				<th colspan="3"></th>
			</tr>
			<c:forEach items="${matieres}" var="m">
				<tr>
					<td>${m.idMatiere}</td>
					<td>${m.nomMatiere}</td>
					<td>${m.couleurMatiere}</td>
					<td>
					
 					<a href="edit/${m.idMatiere}" class="btn btn-warning btn-sm"><i class="fa fa-pencil"></i></a> 
 					<a href="delete/${m.idMatiere}" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></a> 
					</td>
				</tr>
			</c:forEach>
				<tr>
				<td colspan="10"><a href="add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i></a></td>
			</tr>
		</table>
	</div>
		
<jsp:include page="../elements/footer.jsp" />