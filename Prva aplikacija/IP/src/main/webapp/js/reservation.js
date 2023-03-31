jQuery(document).ready(function(){
  $("#state").change(function() {
  
  	var stateId = document.getElementById("state");
  
    $.ajax({
     url: "http://localhost:8080/IP/?action=townsJSON",
     method: "POST",
     data: {"stateId" : stateId.value},
     success: function(data){
      	
      	
      	$('#town').empty();
      	
    var towns = JSON.parse(data); 	
  	var townCount = towns.length;
     	
	for (var i = 0; i < townCount; i++) {
    	
    	$('#town').append("<option>" + towns[i].name + "</option>");
   
	}
      
     }
   });
  });
});