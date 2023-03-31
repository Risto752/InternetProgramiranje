function cancel(reservationId){

		
  		 $.ajax({
     url: "http://localhost:8080/IP/?action=cancelReservaton",
     method: "POST",
     data: {"reservationId" : reservationId},
     success: function(){
      	
      	
      	document.getElementById("button" + reservationId).style.display = "none"; // pobrisi dugme ponisti
    	document.getElementById("status" + reservationId).innerHTML = "ponistena"; // promjeni status na ponistena
      
     }
   });



}