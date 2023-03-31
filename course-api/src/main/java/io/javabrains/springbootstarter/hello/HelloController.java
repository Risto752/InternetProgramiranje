package io.javabrains.springbootstarter.hello;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {
	
	@Autowired
	private DatabaseService databaseService;
	


	@RequestMapping(method=RequestMethod.POST, value="/login")
	public boolean login(@RequestBody User user) {
		
		String username = user.username;
		String password = user.password;
		
		return  databaseService.login(username,password);

		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/reservations/{status}")
	public ArrayList<Reservation> getReservations(@PathVariable String status) {
		
		return databaseService.getReservations(status);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/acceptReservation/{id}")
	public void acceptReservation(@PathVariable int id) {
		
		 databaseService.acceptReservation(id);
		 return;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/declineReservation/{id}/{reason}")
	public void declineReservation(@PathVariable int id, @PathVariable String reason) {
		
	
		 databaseService.declineReservation(id, reason);
		 return;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/messages")
	public ArrayList<Message> getMessages() {
		
	
		 return  databaseService.getMessages();
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/messageSeen/{id}")
	public void messageSeen(@PathVariable int id) {
		
		 databaseService.messageSeen(id);
		 return;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/messages/{content}")
	public ArrayList<Message> filterMessages(@PathVariable String content) {
		
	
		 return  databaseService.filterMessages(content);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/email")
	public void sendEmail(@RequestBody Message answer) {
				
		databaseService.sendEmail(answer);
		
		 return;
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/flights")
	public ArrayList<Flight> getFlights() {
		
	
		 return  databaseService.getFlights();
		
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET, value="/flightLines")
	public ArrayList<FlightLine> getFlightLines() {
		
	
		 return  databaseService.getFlightLines();
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/towns")
	public ArrayList<Town> getTowns() {
		
	
		 return  databaseService.getTowns();
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/createPassengerFlightLine")
	public void createPassengerFlightLine(@RequestBody PassengerFlightLine flightLine) {
				
		
		databaseService.createPassangerFlightLine(flightLine);
		
		 return;
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createTransportFlightLine")
	public void createTransportFlightLine(@RequestBody TransportFlightLine flightLine) {
				
		
		databaseService.createTransportFlightLine(flightLine);
		
		 return;
		
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteFlightLine/{id}")
	public void deleteFlightLine(@PathVariable int id) {
				
		
		databaseService.deleteFlightLine(id);
		
		 return;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/createFlight")
	public void createFlight(@RequestBody Flight flight) {
				
		
		databaseService.createFlight(flight);
		
		 return;
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteFlight/{id}")
	public void deleteFlight(@PathVariable int id) {
				
		
		databaseService.deleteFlight(id);
		
		 return;
		
	}
	
	
}
