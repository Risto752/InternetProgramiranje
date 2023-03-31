// funkcija koja se okida pri otvaranju modala
$(document).ready(function () { 
     $('#registerModal').on('shown.bs.modal', function (e) {
           
		   restFunc();
		   
      });
});

// funckija koja poziva rest servis za drzave
const restFunc = async () => {
	
	
		const response = await fetch('https://restcountries.eu/rest/v2/region/europe');
		   const statesJsonArr = await response.json();
		   
		   statesJsonArr.forEach(printCountry);
			
}

//funkcija koja dodaje pojedinacni element(ime drzave) u dropdown meni
function printCountry(value, index, array) {
  
	var dropdown = document.getElementById("exampleInputState");
	var option = document.createElement("option");
	option.text = value.name;
	
	dropdown.add(option);
	
  
}
 
// funkcija provjerava da li je korisnik ulogovan
const isLoggedIn = async () => {
	
	var name = document.getElementById("logInUsername").value
	var password = document.getElementById("logInPassword").value
	
	// validacija forme
	
	 if (name == "") { 
        window.alert("Please enter username"); 
        name.focus(); 
        return false; 
    } 
	
	 if (password == "") { 
	        window.alert("Please enter password"); 
	        password.focus(); 
	        return false; 
	    } 
	
	let formData = new FormData();
	formData.append('username', name);
	formData.append('password', password);
	
	
	const response = await fetch('http://localhost:8080/IP/?action=login', {
		 method: 'POST',
		body: formData
	});
	   const isLoggedIn = await response.json();
	  
	   
	  
	   if(JSON.stringify(isLoggedIn) === "false"){
		   
		   var divErr = document.getElementById("error")
		   
		   divErr.style.display = 'block'
		   
	   }else{
		   
		   window.location.href = "http://localhost:8080/IP/"
		   
	   }
	   
		
}

//registracija
const registration = async () => {
	
	
	// blok koda koji provjerava da li se lozinke poklapaju
	var password1 = document.getElementById("exampleInputPassword1");  
	  var password2 = document.getElementById("exampleInputPassword2");  
	  if(password1.value != password2.value)  
	  {   
		 alert("Passwords did not match, try again")
		
	  }else{ 
	
	
		  
	var password2 = document.getElementById("exampleInputPassword2").value
	
	var email = document.getElementById("exampleInputEmail1").value
	var username = document.getElementById("exampleInputUsername").value
	var password = document.getElementById("exampleInputPassword1").value
	var name = document.getElementById("exampleInputName").value
	var surname = document.getElementById("exampleInputSurname").value
	var address = document.getElementById("exampleInputAddress").value
	var state = document.getElementById("exampleInputState")
	var strState = state.options[state.selectedIndex].text
	
	var transportType;
	
	if (document.getElementById('flexRadioDefault1').checked) {
		
		transportType = document.getElementById('flexRadioDefault1').value;
		
		}else{
			
		transportType = document.getElementById('flexRadioDefault2').value;
		
		}
	
	// validacija forme
	
	 if (email == "") { 
         window.alert("Please enter email"); 
         email.focus(); 
         return false; 
     } 
	 
	 if (username == "") { 
         window.alert("Please enter username"); 
         username.focus(); 
         return false; 
     } 
	 if (password == "") { 
         window.alert("Please enter password"); 
         password.focus(); 
         return false; 
     } 
	 
	 if (password2 == "") { 
         window.alert("Please verify password"); 
         password2.focus(); 
         return false; 
     } 
	
	 if (name == "") { 
         window.alert("Please enter name"); 
         name.focus(); 
         return false; 
     } 
	 if (surname == "") { 
         window.alert("Please enter surname"); 
         surname.focus(); 
         return false; 
     } 
	 if (address == "") { 
         window.alert("Please enter address"); 
         address.focus(); 
         return false; 
     } 
	
	 if(!(document.getElementById('flexRadioDefault1').checked || document.getElementById('flexRadioDefault2').checked)){
		  
		 window.alert("Please choose type of transport"); 
		 return false;
		 
	 }
	 
	
	
	let formData = new FormData();
	
	formData.append('email', email);
	formData.append('username', username);
	formData.append('password1', password);
	formData.append('name', name);
	formData.append('surname', surname);
	formData.append('address', address);
	formData.append('state', strState);
	formData.append('flexRadioDefault', transportType);
	
	
	const response = await fetch('http://localhost:8080/IP/?action=register', {
		 method: 'POST',
		body: formData
	});
	   const registration = await response.json();
	
	   
	  
	   if(JSON.stringify(registration) === "false"){
		   
		   alert("E-mail already exists")
		   
	   }else{
		   
		 
		   window.location.href = "http://localhost:8080/IP/"
		   
	   }
	   
	  }
		
}

//funkcija koja dobavlja letove u json formatu
const updateFlightsFunc = async () => {
	
	
	const responseDepatures = await fetch('http://localhost:8080/IP/?action=flightsDepaturesJSON');
	   const flightsDepJsonArr = await responseDepatures.json();
	   
	   var table = document.getElementById("depaturesTable");
	   
	   while(table.rows.length > 0) {
		   table.deleteRow(0);
		 }
	   
	   for(let i = 0; i < flightsDepJsonArr.length; i++) {
		    let flight = flightsDepJsonArr[i];

		    let row = table.insertRow(i);
		    
		    let destinationCell = row.insertCell(0);
		    let takeOffCell = row.insertCell(1);
		    let propertyCell = row.insertCell(2);
		    let statusCell = row.insertCell(3);
		    let flightTypeCell = row.insertCell(4);
		    
		    destinationCell.innerHTML = flight.destination;
		    takeOffCell.innerHTML = flight.departTime;
		    
		    if(flight.type === "Putnicki prevoz"){
		    propertyCell.innerHTML = flight.seatCount;
		    }else{
		    propertyCell.innerHTML = flight.cargoDescription;
		    }
		    
		    flightTypeCell.innerHTML = flight.type;
		    
		   // trenutno vrijeme
		    let currentdate = new Date();
		    let currentDateTime = new Date(currentdate.getFullYear(), currentdate.getMonth()+1, currentdate.getDate(), currentdate.getHours(), currentdate.getMinutes(), currentdate.getSeconds());
		    
		  
		    
		    let departTime = flight.departTime;
		    
		    let parts = departTime.split(" ");
		    
		    let YYMMDD = parts[0];
		    let HHMMSS = parts[1];
		    
		    let parts1 = YYMMDD.split("-");
		    let parts2 = HHMMSS.split(":");
		    
		    let year = parts1[0];
		    let month = parts1[1];
		    let day = parts1[2];
		    
		    let hour = parts2[0];
		    let minute = parts2[1];
		    let seconds = parts2[2];
		    
		    // vrijeme kad zapravo poljece avion
		   let departDateTime = new Date(year, month, day, hour, minute, seconds);
		    
		  
		   if (currentDateTime.getTime() < departDateTime.getTime()){
			   
			   statusCell.innerHTML = "Waiting";
			   
		   }else{
			   
			   statusCell.innerHTML = "Took of";
			   
		   }
		     
		  
		    
		}
	   
	   const responseArrival = await fetch('http://localhost:8080/IP/?action=flightsArrivalJSON');
	   const flightsArrivalJsonArr = await responseArrival.json();
		
	   table = document.getElementById("arrivalsTable");
	   
	   while(table.rows.length > 0) {
		   table.deleteRow(0);
		 }
	   for(let i = 0; i < flightsArrivalJsonArr.length; i++) {
		    let flight = flightsArrivalJsonArr[i];

		    let row = table.insertRow(i);
		    
		    let originCell = row.insertCell(0);
		    let landingTimeCell = row.insertCell(1);
		    let propertyCell = row.insertCell(2);
		    let statusCell = row.insertCell(3);
		    let flightTypeCell = row.insertCell(4);
		    
		    originCell.innerHTML = flight.origin;
		    landingTimeCell.innerHTML = flight.arriveTime;
		    
		    if(flight.type === "Putnicki prevoz"){
		    propertyCell.innerHTML = flight.seatCount;
		    }else{
		    propertyCell.innerHTML = flight.cargoDescription;
		    }
		    
		    flightTypeCell.innerHTML = flight.type;
		    
		   // trenutno vrijeme
		    let currentdate = new Date();
		    let currentDateTime = new Date(currentdate.getFullYear(), currentdate.getMonth()+1, currentdate.getDate(), currentdate.getHours(), currentdate.getMinutes(), currentdate.getSeconds());
		    
		  
		    
		    let arriveTime = flight.arriveTime;
		    
		    let parts = arriveTime.split(" ");
		    
		    let YYMMDD = parts[0];
		    let HHMMSS = parts[1];
		    
		    let parts1 = YYMMDD.split("-");
		    let parts2 = HHMMSS.split(":");
		    
		    let year = parts1[0];
		    let month = parts1[1];
		    let day = parts1[2];
		    
		    let hour = parts2[0];
		    let minute = parts2[1];
		    let seconds = parts2[2];
		    
		    // vrijeme kad slijece avion
		   let arrivalDateTime = new Date(year, month, day, hour, minute, seconds);
		    
		  
		   if (currentDateTime.getTime() < arrivalDateTime.getTime()){
			   
			   statusCell.innerHTML = "Waiting";
			   
		   }else{
			   
			   statusCell.innerHTML = "Landed";
			   
		   }
		     
		  
		    
		}
}


// funkcija koja azurira letove svakih 1 min
var intervalId = setInterval(function() {
	  
	
	updateFlightsFunc();
	   
	
	
	}, 5000);




