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
		<h1>Edition d'une salle</h1>
	</div>

	<div class="col-md-5 col-sm-12">
		<form:form modelAttribute="salle" action="${pageContext.request.contextPath}/salle/save" method="post" cssClass="form-horizontal">
			<form:hidden path="id" readonly="${mode == 'edit'}" />
			<form:hidden path="version" />
			<input name="mode" type="hidden" value="${mode}">

			<c:choose>
					<c:when test="${isAdmin == 1}">
			<div class="form-group">
				<form:label path="etablissement">Type</form:label>
				<form:select path="etablissement.id" id="etablissement" cssClass="form-control">
					<form:option value="${null}">Choisir l'établissement</form:option>
					<c:forEach items="${etab}" var="et">
						<form:option value="${et.id}">${et.nom}</form:option>
					</c:forEach>
				</form:select>
			</div>
					</c:when>
					<c:otherwise>
						<form:input type="hidden" path="etablissement.id" name="etab_id" value="${etabId}" ></form:input>
					</c:otherwise>
				</c:choose>
				
			<div class="form-group">
				<form:label path="nom">Nom de la salle</form:label>
				<form:input path="nom" type="text" cssClass="form-control" value="${nomSal}"/>
				<form:errors path="nom" cssStyle="color:red"></form:errors>
			</div>

			<div class="form-group">
				<form:label path="capacite">Capacité</form:label>
				<form:input path="capacite" type="number" cssClass="form-control" value="${capSal }" />
				<form:errors path="capacite" cssStyle="color:red"></form:errors>
			</div>
			<button type="submit" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer </button>

		</form:form>
	</div>
</div>

<jsp:include page="../elements/footer.jsp" />