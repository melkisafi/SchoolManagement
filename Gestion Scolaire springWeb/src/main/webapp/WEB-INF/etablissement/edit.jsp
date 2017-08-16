<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>Edition d'un établissement</h1>
		</div>
		
		<div class="col-md-5 col-sm-12">
			<form:form modelAttribute="etablissement" action="/GestionScolaireSpringWeb/etablissement/save" method="post" cssClass="form-horizontal">
				<form:hidden path="id" readonly="${mode = 'edit'}"/>
				<form:hidden path="version" />
				<input name="mode" type="hidden" value="${mode}" >	
				
				<div class="form-group">
				  <form:label path="nom">Nom</form:label>
				  <form:input path="nom" type="text" cssClass="form-control" />
				  <form:errors path="nom" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="type">Type</form:label>
				  <form:select path="type" cssClass="form-control">
				  	<form:option value="${null}">Choisir</form:option>
				  	<form:options items="${type}"></form:options>
				  </form:select>
				  <form:errors path="type" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="tel">Tel</form:label>
				  <form:input path="tel" type="number" cssClass="form-control" />
				  <form:errors path="tel" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="adr.adresse">Adresse</form:label>
				  <form:input path="adr.adresse" type="text" cssClass="form-control" />
				  <form:errors path="adr.adresse" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="adr.codepostal">Code postal</form:label>
				  <form:input path="adr.codepostal" type="text" cssClass="form-control" />
				  <form:errors path="adr.codepostal" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="adr.ville">Ville</form:label>
				  <form:input path="adr.ville" type="text" cssClass="form-control" />
				  <form:errors path="adr.ville" cssStyle="color:red"></form:errors>
				</div>
				
				<div class="form-group">
				  <form:label path="adr.pays">Pays</form:label>
				  <form:input path="adr.pays" type="text" cssClass="form-control" />
				  <form:errors path="adr.pays" cssStyle="color:red"></form:errors>
				</div>
						 
				<button type="submit" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer</button>
				
			</form:form>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />