<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>Edition d'une classe </h1>
		</div>
		<div class="col-md-5 col-sm-12">
			<form:form modelAttribute="classe" action="/GestionScolaireSpringWeb/classe/save" method="post" cssClass="form-horizontal">
				<form:hidden path="id" readonly="${mode == 'edit'}"/>
				<form:hidden path="version" />
				<input name="mode" type="hidden" id="mode" value="${mode}" >	
				
				<div class="form-group">
				  <form:label path="nom">Nom</form:label>
				  <form:input path="nom" type="text" cssClass="form-control" />
				</div>

				<div class="form-group" id="profs">
					<label for="professeurs">Professeur principal</label>
					<select name="personne_id" id="professeurs" class="form-control">
						<c:choose>
							<c:when test="${mode == 'edit'}"><option value="${pp.id}" id="pp" data-type="pp" data-etab="${etab.id}">${pp.nom}</option></c:when>
							<c:otherwise><option value="${null}"></option></c:otherwise>
						</c:choose>
						<c:if test="${isAdmin == 0 || mode == 'edit'}">
							<c:forEach items="${profs}" var="pr">
								<c:if test="${pp.id != pr.id}"><option class="opts-pp" value="${pr.id}">${pr.nom}</option></c:if>
							</c:forEach>
						</c:if>
					</select>
				</div>
				
				<c:choose>
					<c:when test="${isAdmin == 1}">
						<div class="form-group">
							<label for="etab">Etablissement</label>
							<select name="etab_id" id="etab" class="form-control">
								<c:choose>
									<c:when test="${mode == 'edit'}"><option value="${etab.id}">${etab.nom }</option></c:when>
									<c:otherwise><option value="${null}"></option></c:otherwise>
								</c:choose>
								<c:forEach items="${etabs}" var="e">
									<c:if test="${etab.id != e.id}"><option value="${e.id}">${e.nom}</option></c:if>
								</c:forEach>
							</select>
						</div>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="etab_id" value="${etab.id}" >
					</c:otherwise>
				</c:choose>
			
				
				<button type="submit" id="formClasse" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer</button>
				
			</form:form>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />