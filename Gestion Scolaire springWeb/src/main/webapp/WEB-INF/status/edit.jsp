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
		<div class="page-header"><h1>Editions des status</h1></div>
	</div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="col-md-5 col-sm-12">
			<form:form modelAttribute="status" action="/GestionScolaireSpringWeb/status/save" method="post" csssClass="form-horizontal">
				<form:hidden path="id" readonly="${mode = 'edit'}"/>
				<form:hidden path="version" />
				<input name="mode" type="hidden" value="${mode}" >	
				
				<div class="form-group">
				  <form:label path="nom">Nom</form:label>
				  <form:input path="nom" type="text" cssClass="form-control" />
				</div>
				
				<button type="submit" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer</button>
			</form:form>
		</div>
	</div>
<jsp:include page="../elements/footer.jsp" />