<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<jsp:include page="../elements/header.jsp" />
<jsp:include page="../elements/sidebar.jsp" />
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="page-header"><h1>Etablissement <small>${etab.nom}</small></h1></div>
		
		<div class="row">

            <div class="col-xs-12 col-sm-4">
                <div class="stat">
                    <div class="icon-stat icon-user">
                    <i class="fa fa-user-circle-o"></i>
                  </div>
                  <div class="stat-content">
                      <h1>${nbProf}</h1>
                      <h5>Professeurs</h5>                     
                  </div>
                  <div class="clearfix"></div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-4">
                <div class="stat">
                   <div class="icon-stat icon-salle">
                      <i class="fa fa-group"></i>
                    </div>
                    <div class="stat-content">
                      <h1>${nbSalle}</h1>
                      <h5>Salles</h5>                     
                    </div>
                    <div class="clearfix"></div>
                 </div>
            </div>

            <div class="col-xs-12 col-sm-4">
                <div class="stat">
                   <div class="icon-stat icon-classe">
                      <i class="fa fa-graduation-cap"></i>
                    </div>
                    <div class="stat-content">
                      <h1>${nbClasse}</h1>
                      <h5>Classes</h5>                     
                    </div>
                    <div class="clearfix"></div>
                 </div>
            </div>

          </div>
	</div>
	
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="col-md-6 col-sm-12 block-nopadding-l">
			<div class="panel panel-default">
				<div class="panel-heading">
					Adresse
					<span class="separator"></span>
				</div>
				<div class="panel-body">
			
					<adress>
						<strong>${etab.nom}</strong> <small>${etab.type}</small> <br>
						<strong>${adr.adresse}</strong><br>	
						${adr.codepostal},
						${adr.ville}<br>
						<strong>${adr.pays}</strong><br>
						Tel : ${etab.tel}
					</adress>
					<br>
					<div class="clearfix"></div>
					<a href="../edit/${etab.id}" class="btn btn-warning btn-sm pull-right"><i class="fa fa-pencil"></i> Editer</a>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-12 block-nopadding-r">
			<div class="panel panel-default">
				<div class="panel-heading">Professeurs<span class="separator"></span></div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>Nom</th>
									<th>Prenom</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${profs}" var="p">
									<tr>
										<td>${p.id}</td>
										<td>${p.nom}</td>
										<td>${p.prenom}</td>
										<td><a href="/GestionScolaireSpringWeb/utilisateur/edit/${p.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil"></i></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="col-md-6 col-sm-12 block-nopadding-l">
			<div class="panel panel-default">
				<div class="panel-heading">Salles<span class="separator"></span></div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>Nom</th>
									<th>Capacite</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${salles}" var="s">
									<tr>
										<td>${s.id}</td>
										<td>${s.nom}</td>
										<td>${s.capacite}</td>
										<td><a href="/GestionScolaireSpringWeb/salle/edit/${s.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil"></i></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-12 block-nopadding-r">
			<div class="panel panel-default">
				<div class="panel-heading">Classes</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>Nom</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${classes}" var="c">
									<tr>
										<td>${c.id}</td>
										<td>${c.nom}</td>
										<td>
											<a href="/GestionScolaireSpringWeb/classe/voir/${c.id}" class="btn btn-info btn-sm"><i class="fa fa-search"></i></a>
										
											<a href="/GestionScolaireSpringWeb/classe/edit/${c.id}" class="btn btn-warning btn-sm"><i class="fa fa-pencil"></i></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />