		<nav class="navbar navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"><i class="fa fa-bars"></i></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <!--
         <form action="/PCentreAppel/Recherche" method="post" class="navbar-form navbar-right">
        	 <div class="form-group">
				<div class="input-group">	
					<span class="input-group-addon" id="calendar"><i class="fa fa-calendar"></i></span>
					<input type="date" name="date" class="form-control" aria-describedby="calendar" >
				</div>
			</div>
        	 <div class="form-group">
				<div class="input-group">
					<span class="input-group-addon" id="name-select"><i class="fa fa-user"></i></span>
		            <select name="nom" class="form-control" aria-describedby="name-select">
						<option value="">Choisir un nom d'utilisateur</option>
					
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon" id="lastname-select"><i class="fa fa-user"></i></span>
		           	<select name="prenom" class="form-control" aria-describedby="lastname-select">
						<option value="">Choisir un prenom d'utilisateur</option>
						
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon" id="centre-select"><i class="fa  fa-building-o"></i></span>
					<select name="centre" class="form-control" aria-describedby="centre-select">
						<option value="">Choisir un centre</option>
					
					</select>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">
			 	 <i class="fa fa-search"></i>
			</button>
          </form>
            -->
            
        </div>
      </div>
    </nav>

    <div class="container-fluid">

      <div class="row">

        <div class="col-sm-3 col-md-2 sidebar">

          <div class="brand">4FANTASTICS</div>
            <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
                
          <div class="nav-side-user">
            <img src="${pageContext.request.contextPath}/ressources/img/avatar.png" alt="avatar">
            <div class="nav-side-user-info">
              <p>Maquaire Jérémy</p>
            </div>
            <div class="clearfix"></div>
          </div>

          <ul id="menu-content" class="menu-content nav nav-sidebar collapse in">
          	  <li class="dashboard-border">
          	  	<a href="${pageContext.request.contextPath}/login/logout"><i class="fa fa-power-off"></i> Logout</a>
          	  </li>
              <li class="dashboard-border">
                  <a href="${pageContext.request.contextPath}/"><i class="fa fa-dashboard fa-lg"></i> Dashboard</a>
              </li>
             
              <li  data-toggle="collapse" data-target="#users-actions" class="collapsed user-border">
                  <a href="#"><i class="fa fa-user"></i> Utilisateurs <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="users-actions">
                  <li><a href="${pageContext.request.contextPath}/personne/add">Ajouter un utilisateur</a></li>
                  <li><a href="${pageContext.request.contextPath}/personne/list">Liste des utilisateur</a></li>
              </ul>


              <li  data-toggle="collapse" data-target="#center-actions" class="collapsed center-border">
                  <a href="#"><i class="fa fa-bank"></i> Etablisements <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="center-actions">
                  <li><a href="${pageContext.request.contextPath}/etablissement/add">Ajouter un etablissement</a></li>
                  <li><a href="${pageContext.request.contextPath}/etablissement/list">Liste des etablissements</a></li>
              </ul>
              
              
               <li  data-toggle="collapse" data-target="#subject-actions" class="collapsed center-border">
                  <a href="#"><i class="fa fa-book"></i> Matières <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="subject-actions">
                  <li><a href="${pageContext.request.contextPath}/matiere/add">Ajouter une matière</a></li>
                  <li><a href="${pageContext.request.contextPath}/matiere/list">Liste des matières</a></li>
              </ul>
              
               <li data-toggle="collapse" data-target="#room-actions" class="collapsed center-border">
                  <a href="#"><i class="fa fa-group"></i> Salles <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="room-actions">
                  <li><a href="${pageContext.request.contextPath}/salle/add">Ajouter une salle</a></li>
                  <li><a href="${pageContext.request.contextPath}/salle/list">Liste des salles</a></li>
              </ul>
                <li  data-toggle="collapse" data-target="#classe-actions" class="collapsed center-border">
                  <a href="#"><i class="fa fa-graduation-cap"></i> Classes <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="classe-actions">
                  <li><a href="${pageContext.request.contextPath}/classe/add">Ajouter une classe</a></li>
                  <li><a href="${pageContext.request.contextPath}/classe/list">Liste des classes</a></li>
              </ul>
               <li  data-toggle="collapse" data-target="#prof-actions" class="collapsed center-border">
                  <a href="#"><i class="fa fa-user-circle-o"></i> Professeurs <span class="arrow"></span></a>
              </li>
              <ul class="sub-menu collapse" id="prof-actions">
                  <li><a href="${pageContext.request.contextPath}/personne/addprof">Ajouter un professeur</a></li>
                  <li><a href="${pageContext.request.contextPath}/personne/list/id">Liste des professeur</a></li>
              </ul>
             <!-- 
             <li  class="collapsed dashboard-border">
                  <a href="recherche.jsp"><i class="fa fa-search"></i> Recherche </a>
              </li> -->
          </ul>
        </div>
       </div>
     </div>
        
		