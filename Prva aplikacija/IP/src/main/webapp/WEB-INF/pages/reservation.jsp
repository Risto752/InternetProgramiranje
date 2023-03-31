<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
     <%@ page import="java.util.ArrayList" %>
    <%@ page import="dto.PassengerDepature" %>
    <%@ page import="dto.TransportDepature" %>
       <%@ page import="dto.State" %>
         <%@ page import="dto.Town" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ page import="java.util.Date" %>
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
  <script src="js/reservation.js"></script>
</head>
<body>


  
<div class="container">
	
	<jsp:include page="header.jsp" />
  
 
<form action="http://localhost:8080/IP/?action=allreservations" method="post">
  <label for="state">Choose state:</label>
  <select name="state" id="state">
  
  <%
  ArrayList<State> states = (ArrayList<State>) request.getAttribute("states"); 
  for(State state : states){ 
  			
  %>
    <option value=<%= state.getStateId() %>><%= state.getName() %></option>
   
   <%
  }
   %>
   
  </select>
  <br><br>
  
  <label for="town">Choose town:</label>
  <select name="town" id="town">
  
  <%
  ArrayList<Town> towns = (ArrayList<Town>) request.getAttribute("towns"); 
  for(Town town : towns){ 
  			
  %>
    <option value=<%= town.getName() %>><%= town.getName() %></option>
   
   <%
  }
   %>
   
  </select>
  <br><br>
  <label for="start">Take off time:</label>

<input type="date" id="start" name="trip-start" required>
  <br><br>
  
  
  <input type="submit" value="Search flights">
  
    <br><br>
  
  
  
</form>
 
 
 
  
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
    <label for="exampleInputAddress">Address</label>
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
    <label for="logInPassword">Password</label>
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
    