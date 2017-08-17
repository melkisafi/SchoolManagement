<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header">
			<h1>Edition d'un utilisateur</h1>
		</div>
		
		<div class="col-md-12 col-sm-12 block-nopadding-l block-nopadding-r" >
			<form:form modelAttribute="personne" action="/GestionScolaireSpringWeb/utilisateur/save" method="post" cssClass="form-horizontal">
				<form:hidden path="id" readonly="${mode == 'edit'}"/>
				<form:hidden path="version" />
				<input name="mode" type="hidden" value="${mode}" >	
				
				<div class="col-md-3 col-sm-12 form-group-column">
					<div class="page-header">
						<h3>Informations</h3>
					</div>
					
					<div class="form-group">
					  <form:label path="civilite">Civilite</form:label>
					  <form:select path="civilite" cssClass="form-control">
					  	<form:option value="${null}">Choisir</form:option>
					  	<form:options items="${civilites}"></form:options>
					  </form:select>
					</div>
					
					<div class="form-group">
					  <form:label path="nom">Nom</form:label>
					  <form:input path="nom" type="text" cssClass="form-control" />
					</div>
					
					<div class="form-group">
					  <form:label path="prenom">Prenom</form:label>
					  <form:input path="prenom" type="text" cssClass="form-control" />
					</div>
					
					<div class="form-group">
					  <form:label path="datenaiss">Date de naissance</form:label>
					  <form:input path="datenaiss" type="date" cssClass="form-control" />
					</div>
				</div>
				
				<div class="col-md-3 col-sm-12 form-group-column">
					<div class="page-header">
						<h3>Adresses</h3>
					</div>
					
					<div class="form-group">
					  <form:label path="adresse.adresse">Adresse</form:label>
					  <form:input path="adresse.adresse" type="text" cssClass="form-control" />
					</div>
					
					<div class="form-group">
					  <form:label path="adresse.codepostal">Code postal</form:label>
					  <form:input path="adresse.codepostal" type="text" cssClass="form-control" />
					</div>
					
					<div class="form-group">
					  <form:label path="adresse.ville">Ville</form:label>
					  <form:input path="adresse.ville" type="text" cssClass="form-control" />
					</div>
					
					<div class="form-group">
					  <form:label path="adresse.pays">Pays</form:label>
					  <form:input path="adresse.pays" type="text" cssClass="form-control" />
					</div>
					
				</div>		 
				
				<div class="col-md-3 col-sm-12">
					<div class="page-header">
						<h3>Restrictions</h3>
					</div>
					
					<div class="form-group">
						<label for="etablissement">Etablissement</label>
						<select class="form-control" id="etablissement" name="etablissement_id">
							<c:choose>
								<c:when test="${mode == 'edit'}"><option value="${etablissement.id}">${etablissement.nom}</option></c:when>
								<c:otherwise>
									<option value="">Choisir</option>
								</c:otherwise>
							</c:choose>
								
							<c:forEach var="e" items="${etabs}">
								<c:if test="${etablissement.id != e.id}"><option value="${e.id}">${e.nom}</option></c:if>
							</c:forEach>
						</select>
					</div>
					
					<div class="form-group">
						<form:label path="role">Role</form:label>
						<form:select path="role" cssClass="form-control">
							<form:option value="${null}">Choisir</form:option>
					  		<form:options items="${roles}"></form:options>
						</form:select>
					</div>
					<div class="form-group">
						<form:label path="statusEnum">Status</form:label>
						<form:select path="statusEnum" cssClass="form-control">
							<form:option value="${null}">Choisir</form:option>
					  		<form:options items="${statusenums}"></form:options>
						</form:select>
					</div>
				</div>
				<div class="col-md-3 col-sml-12 form-group-column">
					<div class="page-header">
						<h3>Login</h3>
					</div>
					
					<div class="form-group">
					  <label for="username">login</label>
					<c:choose>
					  	<c:when test="${mode == 'edit'}">  <input id="username" name="username" type="text" class="form-control" value="${login.username}" /></c:when>
						  <c:otherwise>
						  	<input id="username" type="text" name="username" class="form-control"  required/>
						  </c:otherwise>
					  </c:choose>
					 </div>
					
					<div class="form-group">
					  <label for="password">Password</label>
					  <c:choose>
					  	<c:when test="${mode == 'edit'}">  <input id="password" name="password" type="password" class="form-control" value="${login.password}" /></c:when>
						  <c:otherwise>
						  	<input id="password" type="password" name="password" class="form-control" required/>
						  </c:otherwise>
					  </c:choose>
					</div>
				</div>
				<div class="col-md-12">
				<button type="submit" class="btn btn-warning pull-right"><i class="fa fa-pencil"></i> Editer</button>
				</div>
			</form:form>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />