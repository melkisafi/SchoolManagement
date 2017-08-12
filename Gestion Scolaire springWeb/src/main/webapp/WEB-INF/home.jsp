<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="elements/header.jsp" />
<jsp:include page="elements/sidebar.jsp" />
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<c:if test="${typeMess != null }">
				<div class="alert alert-${typeMess}" role="alert">${message}</div>
			</c:if>
		</div>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div class="page-header"><h1>DASHBOARD</h1></div>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	
          <div class="row">
          
            <div class="col-xs-12 col-sm-4">

              <div class="action">
                <a href="addUser.jsp">
                  <i class="fa fa-user"></i>
                  <div class="action-plus action-user"><i class="fa fa-plus"></i></div>
                </a>
              </div>

            </div>

            <div class="col-xs-12 col-sm-4">
              <div class="action">
                <a href="./etablissement/add">
                  <i class="fa fa-bank"></i>
                  <div class="action-plus action-center"><i class="fa fa-plus"></i></div>
                </a>
              </div>
            </div>

            <div class="col-xs-12 col-sm-4">
              <div class="action">
                <a href="./matiere/add">
                  <i class="fa fa-book"></i>
                  <div class="action-plus action-phone"><i class="fa fa-plus"></i></div>
                </a>
              </div>
            </div>

          </div>
        </div>

		<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/ressources/js/script.js"></script>
	</body>
</html>