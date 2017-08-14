<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
                      <h1></h1>
                      <h5>Personnes</h5>                     
                  </div>
                  <div class="clearfix"></div>
                </div>
            </div>

            <div class="col-xs-12 col-sm-4">
                <div class="stat">
                   <div class="icon-stat icon-center">
                      <i class="fa fa-group"></i>
                    </div>
                    <div class="stat-content">
                      <h1></h1>
                      <h5>Salles</h5>                     
                    </div>
                    <div class="clearfix"></div>
                 </div>
            </div>
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="col-md-6 col-sm-12 block-nopadding-l">
			<div class="panel panel-default">
				<div class="panel-heading">Salles</div>
				<div class="panel-body">
					
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-12 block-nopadding-r">
			<div class="panel panel-default">
				<div class="panel-heading">Personnes</div>
				<div class="panel-body">
					
				</div>
			</div>
		</div>
	</div>

<jsp:include page="../elements/footer.jsp" />