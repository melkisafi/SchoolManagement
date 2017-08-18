<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header"><h1>Classe <small>${classe.nom}</small></h1></div>
		<div class="row">
		  <div class="col-xs-12 col-sm-4">
                <div class="stat">
                    <div class="icon-stat icon-user">
                    <i class="fa fa-user-circle-o"></i>
                  </div>
                  <div class="stat-content">
                      <h1>${prof.nom} ${prof.prenom}</h1>
                      <h5>Professeurs principal</h5>                     
                  </div>
                  <div class="clearfix"></div>
                </div>
            </div>

            <div class="hidden-sm col-sm-4">
               
            </div>

            <div class="col-xs-12 col-sm-4">
                <div class="stat">
                   <div class="icon-stat icon-center">
                      <i class="fa fa-bank"></i>
                    </div>
                    <div class="stat-content">
                      <h1>${etab.nom}</h1>
                      <h5>Etablissement</h5>                     
                    </div>
                    <div class="clearfix"></div>
                 </div>
            </div>
        </div>
        
		<div class="page-header"><h3>Agenda</h3></div>
		<div class="row" >
			<div id="calendar"></div>
		</div>
	
	</div>

	<div class="modal fade" tabindex="-1" role="dialog" id="modal">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Edition d'un événement</h4>
	      </div>
	      <div class="modal-body">
	       <form>
	       	<input type="hidden" name="classe_id" value="${classe.id}">
	       	<input type="hidden" name="etab_id" value="${etab.id}">
	       	
	       	<div class="form-group" id="prfs">
				<label for="pr">Professeurs</label>
				<select name="personne_id" id="pr" class="form-control">
					<c:forEach items="${profs}" var="pr">
						<option value="${pr.id}">${pr.nom}</option>
					</c:forEach>		
				</select>
			</div>
			<div class="form-group" >
				<label for="matieres">Matieres</label>
				<select name="matiere_id" id="matieres" class="form-control">
					<c:forEach items="${matieres}" var="m">
						<option value="${m.idMatiere}">${m.nomMatiere}</option>
					</c:forEach>		
				</select>
			</div>
			<div class="form-group" >
				<label for="salles">Salles</label>
				<select name="salle_id" id="salles" class="form-control">
					<c:forEach items="${salles}" var="s">
						<option value="${s.id}">${s.nom}</option>
					</c:forEach>		
				</select>
			</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" data-dismiss="modal" id="button-form-event"><i class="fa fa-pencil"></i> Editer</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	
	
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
               <h4 class="modal-title">Suppression d'un événement</h4>
            </div>
            <div class="modal-body">
                Etes vous sure de vouloir supprimer cet événement ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                <a class="btn btn-danger btn-del"  data-dismiss="modal"><i class="fa fa-trash"></i> Suprrimer</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="viewevent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
               <h4 class="modal-title">Suppression d'un événement</h4>
            </div>
            <div class="modal-body">
               <div class="infevent"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../elements/footer.jsp" />
