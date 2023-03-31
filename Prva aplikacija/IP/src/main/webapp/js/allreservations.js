function reservation(flightId) {
  
  		
  		
  		 $.ajax({
     url: "http://localhost:8080/IP/?action=makereservation",
     method: "POST",
     data: {"flightId" : flightId},
     success: function(){
      	
      	
      	document.getElementById("" + flightId).style.display = "none"; // izbrisi red iz tabele
    	document.getElementById("susscessDiv").style.display = "block"; // prikazi poruku o uspjehu
      
     }
   });
  
}