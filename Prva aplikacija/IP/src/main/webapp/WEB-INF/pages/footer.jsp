  <!-- Kontakt forma -->
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="well well-sm">
                <div id="map1" class="map">
                        </div>
            </div>
        </div>
        <div class="col-md-6">
            <div>
                <div class="panel panel-default">
                   
                    <div class="panel-body text-center">
                     <form class="form-horizontal">
                     
                     <div id="contact"  " role="alert" style="display: none; color: blue">
  					We will respond in shortest time!
					</div>
                     
                    <fieldset>
                        <legend class="text-center header">Contact us</legend>
                        
                       

                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="email" name="email" type="email" placeholder="Email address" class="form-control" required>
                            </div>
                        </div>
						
						 <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="title" name="title" type="text" placeholder="Title" class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <textarea class="form-control" id="message" name="message" placeholder="Enter message for us here." rows="7" required></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="button" onclick='sendFormData()' class="btn btn-primary btn-lg">Send</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
                       
                        <hr />
                        <div>
                        Contact phone: 066 033 362<br />
                        Fax:  +1 323 555 1234<br />
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

function sendFormData(){
	

	
	
	var email = document.getElementById("email").value
	
	if (email == "") { 
        window.alert("Molimo unesite email"); 
        email.focus(); 
        return false; 
    } 
	
	
	
	
	var title = document.getElementById("title").value
	
	
	if (title == "") { 
        window.alert("Molimo unesite naslov"); 
        title.focus(); 
        return false; 
    } 
	
	
	
	var message = document.getElementById("message").value
	
	if (message == "") { 
        window.alert("Molimo unesite poruku"); 
        message.focus(); 
        return false; 
    } 
	
	

		 $.ajax({
		url: "http://localhost:8080/IP/?action=message",
		method: "POST",
		data: {"email" : email,
				"title" : title,
				"message" : message
		
		},
		success: function(){
 	
 	
 		document.getElementById("contact").style.display = "block"
 		
 		
 			 document.getElementById("email").value = ""
 			 document.getElementById("title").value = ""
 			 document.getElementById("message").value = ""
 
			}
		});
	
	
	
}

</script>



<script src="http://maps.google.com/maps/api/js?sensor=false"></script>

<script type="text/javascript">
    jQuery(function ($) {
        function init_map1() {
            var myLocation = new google.maps.LatLng( 44.561739, 17.173060);
            var mapOptions = {
                center: myLocation,
                zoom: 16
            };
            var marker = new google.maps.Marker({
                position: myLocation,
                title: "Property Location"
            });
            var map = new google.maps.Map(document.getElementById("map1"),
                mapOptions);
            marker.setMap(map);
        }
        init_map1();
    });
</script>

<style>
    .map {
        min-width: 300px;
        min-height: 300px;
        width: 100%;
        height: 100%;
    }

    .header {
        background-color: #F5F5F5;
        color: #black;
        height: 70px;
        font-size: 27px;
        padding: 10px;
    }
</style>

 <!-- Futer-->
<div class="page-footer">
<h1 class="text-center">ETFBL 2021 - sva prava zadrzana</h1>
</div>