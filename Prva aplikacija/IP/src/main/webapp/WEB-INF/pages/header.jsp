x<jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>

<div class="page-header">
<div class='btn-toolbar pull-right'>

<%
		if(userBean.isLoggedIn() == true){

	String name = userBean.getUser().getName();
	String surname = userBean.getUser().getSurname();

%>
	<div class='btn-toolbar'>
      <%= name + " " + surname %>
      <br>
      <a class="btn btn-primary" href="http://localhost:8080/IP/?action=logout" role="button">Log out</a>
    </div>
<%}
		else{ %>

    <div class='btn-toolbar'>
      <button type='button' class='btn btn-primary' data-toggle="modal" data-target="#logInModal">Log in</button>
	 <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#registerModal">Register</button>
    </div>
    
    <%} %>
    
  </div>
	<h1 class="text-center">ETFBL_IP_Aero</h1>
	</div>
	
	<!-- Navigacioni meni -->
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <li><a href="http://localhost:8080/IP/">Starting page</a></li>
     
      
      <% 
      
      if(userBean.isLoggedIn() == true){
      
      %>
       <li><a href="http://localhost:8080/IP/?action=allFlightsArrivalsToday">Arrivals</a></li>
      <li><a href="http://localhost:8080/IP/?action=allFlightsDepaturesToday">Depatures</a></li>
      <li><a href="http://localhost:8080/IP/?action=reservation">Flight reservation</a></li>
      <li><a href="http://localhost:8080/IP/?action=myreservations">My reservations</a></li>
      
      <% } %>
    </ul>
  </div>
</nav>
  