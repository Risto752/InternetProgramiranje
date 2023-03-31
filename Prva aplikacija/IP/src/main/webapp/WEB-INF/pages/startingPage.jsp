<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="dto.PassengerDepature" %>
    <%@ page import="dto.TransportDepature" %>
    <%@ page import="dto.PassengerArrival" %>
    <%@ page import="dto.TransportArrival" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
    <%@ page import="java.io.File" %>
    <jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>
  


<!DOCTYPE html>
<html lang="en">
<head>
  <title>ETFBL_IP_Aero</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="js/startingPage.js"></script>
</head>
<body>


  
<div class="container">


<jsp:include page="header.jsp" />
 	 
  <!-- Tabele -->
  <div style='background-image: url("images/light-blue.jpg");background-size:cover;background-repeat:no-repeat' class="container">
  <h3>Arrivals</h1>
 <div class="table-responsive">          
  <table class="table">
  
  
  
    <%
    
		if(userBean.isLoggedIn() == false || (userBean.isLoggedIn() == true && userBean.getUser().getType().equals("Putnicki prevoz"))){
    %>
  
    <thead>
      <tr>
        <th>Starting location</th>
        <th>Landing time</th>
        <th>Seat count</th>
        <th>Status</th>
        <th>Flight type</th>
      </tr>
    </thead>
    <tbody id="arrivalsTable">
    <%			
    ArrayList<PassengerArrival> passengerArrivals = (ArrayList<PassengerArrival>) request.getAttribute("passengerArrivals"); 
    for(PassengerArrival flight1 : passengerArrivals){
    %>
    
      <tr>
        <td><%= flight1.getOrigin() %></td>
        <td><%= flight1.getArriveTime() %></td>
        <td><%= flight1.getSeatCount() %></td>
        <%
        
        SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date currentDateTime1 = sdformat1.parse(sdformat1.format(new Date()));
        Date arrivalDateTime = sdformat1.parse(flight1.getArriveTime());
        
        if(currentDateTime1.compareTo(arrivalDateTime) > 0) {
        
        %>
        <td>Landed</td>
         <%
        }else{
        %>
         <td>Waiting</td>
         <%} %>
        <td><%= flight1.getType() %></td>
      </tr>
        <%  
    }
		}else{
			
			%>
      <thead>
      <tr>
        <th>Starting location</th>
        <th>Landing time</th>
        <th>Cargo description</th>
        <th>Status</th>
        <th>Flight type</th>
      </tr>
    </thead>
    <tbody id="arrivalsTable">
    <%	
		    ArrayList<TransportArrival> transportArrivals = (ArrayList<TransportArrival>) request.getAttribute("transportArrivals"); 
		    for(TransportArrival flight1 : transportArrivals){    
    %>
   <tr>
        <td><%= flight1.getOrigin() %></td>
        <td><%= flight1.getArriveTime() %></td>
        <td><%= flight1.getCargoDescription() %></td>
        <%
        
        SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date currentDateTime1 = sdformat1.parse(sdformat1.format(new Date()));
        Date arrivalDateTime = sdformat1.parse(flight1.getArriveTime());
        
        if(currentDateTime1.compareTo(arrivalDateTime) > 0) {
        
        %>
        <td>Landed</td>
        <%
        }else{
        %>
         <td>Waiting</td>
         <%} %>
        <td><%= flight1.getType() %></td>
      </tr>
      
      <%
		    }
		}
      %>
    </tbody>
  </table>
  </div>
  <h3>Depatures</h1>
  <div class="table-responsive">          
  <table class="table" >
   
    <%
    
		if(userBean.isLoggedIn() == false || (userBean.isLoggedIn() == true && userBean.getUser().getType().equals("Putnicki prevoz"))){
    %>
	<thead>
      <tr>
        <th>Destination</th>
        <th>Take off time</th>
        <th>Seat count</th>
        <th>Status</th>
        <th>Flight type</th>
      </tr>
    </thead>
    <tbody id="depaturesTable">
	<%			
    ArrayList<PassengerDepature> passengerDepatures = (ArrayList<PassengerDepature>) request.getAttribute("passengerDepatures"); 
    for(PassengerDepature flight : passengerDepatures){
    %>
    
      <tr>
        <td><%= flight.getDestination() %></td>
        <td><%= flight.getDepartTime() %></td>
        <td><%= flight.getSeatCount() %></td>
         <%
        
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date currentDateTime = sdformat.parse(sdformat.format(new Date()));
        Date depatureDateTime = sdformat.parse(flight.getDepartTime());
        
        if(currentDateTime.compareTo(depatureDateTime) > 0) {
        
        %>
        <td>Took off</td>
        <%
        }else{
        %>
         <td>Waiting</td>
         <%} %>
        <td><%= flight.getType() %></td>
      </tr>
      
      <%  
    }
		}else{
			
			%>
		<thead>
      <tr>
        <th>Destination</th>
        <th>Take off time</th>
        <th>Cargo description</th>
        <th>Status</th>
        <th>Flight type</th>
      </tr>
    </thead>
    <tbody id="depaturesTable">		
	<%	
		    ArrayList<TransportDepature> transportDepatures = (ArrayList<TransportDepature>) request.getAttribute("transportDepatures"); 
		    for(TransportDepature flight : transportDepatures){    
    %>
      <tr>
        <td><%= flight.getDestination() %></td>
        <td><%= flight.getDepartTime() %></td>
        <td><%= flight.getCargoDescription() %></td>
        <%
        
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date currentDateTime = sdformat.parse(sdformat.format(new Date()));
        Date depatureDateTime = sdformat.parse(flight.getDepartTime());
        
        if(currentDateTime.compareTo(depatureDateTime) > 0) {
        
        %>
        <td>Took of</td>
        <%
        }else{
        %>
         <td>Waiting</td>
         <%} %>
        <td><%= flight.getType() %></td>
      </tr>
      
      <%
		    }
		}
      %>
      
    </tbody>
  </table>
  </div>


	</div>
  
  <jsp:include page="footer.jsp" />

<!-- registracija Modal -->
<div id="registerModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<form >
   
  
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
   
  </div>
  <div class="form-group">
    <label for="exampleInputUsername">Username</label>
    <input type="text" class="form-control" id="exampleInputUsername" name="username" placeholder="Enter username" required>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="password1" placeholder="Enter password" required>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword2">Verify password</label>
    <input type="password" class="form-control" id="exampleInputPassword2" name="password2" placeholder="Verify password" required>
  </div>
  <div class="form-group">
    <label for="exampleInputName">Name</label>
    <input type="text" class="form-control" id="exampleInputName" name="name" placeholder="Enter name" required>
  </div>
   <div class="form-group">
    <label for="exampleInputSurname">Surname</label>
    <input type="text" class="form-control" id="exampleInputSurname" name="surname" placeholder="Enter surname" required>
  </div>
   <div class="form-group">
    <label for="exampleInputAddress">Addresss</label>
    <input type="text" class="form-control" id="exampleInputAddress" name="address" placeholder="Enter address" required>
  </div>
   <div class="form-group">
   <label for="exampleInputAddress">State</label>
  <select class="form-control" id="exampleInputState" name="state">
    
    </select>
	  </div>
	  <div class="form-check">
  <input class="form-check-input" type="radio" value="Putnicki prevoz" name="flexRadioDefault" id="flexRadioDefault1" required>
  <label class="form-check-label" for="flexRadioDefault1">
    Passenger transport
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" value="Teretni prevoz" name="flexRadioDefault" id="flexRadioDefault2">
  <label class="form-check-label" for="flexRadioDefault2">
    Cargo transport
  </label>
</div>
	  
	   <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="registration()">Register</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
</form>
      </div>
    </div>
  </div>
</div>

<!-- Log in Modal -->
<div id="logInModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<form>
  
  
  <div id="error" class="alert alert-danger" style="display:none" role="alert">You have to register first</div>
  
  <div class="form-group">
    <label for="logInUsername">Username</label>
    <input type="text" class="form-control" id="logInUsername" name="logInUsername" placeholder="Enter username" required>
  </div>
  <div class="form-group">
    <label for="logInPassword">Lozinka</label>
    <input type="password" class="form-control" id="logInPassword" name="logInPassword" placeholder="Enter password" required>
  </div>  
	   <div class="modal-footer">
        <button type="button" onclick="isLoggedIn()" class="btn btn-primary">Send</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
</form>
      </div>
    </div>
  </div>
</div>

</div>

</body>
</html>
    