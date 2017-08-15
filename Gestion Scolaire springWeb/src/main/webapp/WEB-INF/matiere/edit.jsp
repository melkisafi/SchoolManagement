<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<c:if test="${typeMess != null }">
			<div class="alert alert-${typeMess}" role="alert">${message}</div>
		</c:if>
	</div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>Edition d'une matiere </h1>
		</div>
		
		<div class="col-md-5 col-sm-12">
			<form:form modelAttribute="matiere" action="/GestionScolaireSpringWeb/matiere/save" method="post" cssClass="form-horizontal">
				<form:hidden path="idMatiere" readonly="${mode == 'edit'}"/>
				<form:hidden path="version" />
				<input name="mode" type="hidden" value="${mode}" >	
				
				<div class="form-group">
				  <form:label path="nomMatiere">Nom</form:label>
				  <form:input path="nomMatiere" type="text" cssClass="form-control" value="${nomMat}" />
				  <form:errors path="nomMatiere" cssStyle="color:red"></form:errors>
				</div>	 
				<div class="form-group">
				  <form:label path="couleurMatiere">couleur</form:label>
				  <form:input path="couleurMatiere" type="text" cssClass="form-control" value="${colMat}" />
				  <form:errors path="couleurMatiere" cssStyle="color:red"></form:errors>
				</div>	
				<button type="submit" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer</button>
				
			</form:form>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />