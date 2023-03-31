package io.javabrains.springbootstarter.hello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Message.RecipientType;

import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	private static String LOGIN = "SELECT * FROM `employee` WHERE `username`=? AND `password`=?";
	private static String RESERVATIONS_SQL = "select * from ((reservation inner join flights on flight_idflight=idflight) inner join `user` on user_id=`user`.id) WHERE `status`=? ORDER BY createTime;";
	private static String ACCEPT_RESERVATION_SQL = "update reservation set status='prihvacena' where id=?";
	private static String DECLINE_RESERVATION_SQL = "update reservation set status='ponistena', declineReason=? where id=?";
	private static String GET_MESSAGES_SQL = "select * from message";
	private static String MESSAGE_SEEN_SQL = "update message set status='procitana' where id=?";
	private static String FILTER_MESSAGE_SQL = "select * from message where message like ?";
	private static String GET_FLIGHTS_SQL = "select * from flights where deleted=0";
	private static String GET_FLIGHT_LINES_SQL = "SELECT idflightLine, flightType, seatCount, cargoDescription, townStart.townName as townStart, townEnd.townName as townEnd FROM ((flightline inner join town as townStart on starting_location=townStart.idtown) inner join town as townEnd on destination=townEnd.idtown) where flightline.deleted=0;";
	private static String GET_TOWNS_SQL = "SELECT * FROM town";
	private static String CREATE_FLIGHT_LINE_SQL = "insert into flightline(flightType,seatCount,cargoDescription,starting_location,destination,deleted) values (?,?,?,?,?,0)";
	private static String DELETE_FLIGHT_LINE_SQL = "update flightline set deleted=1 where idflightLine=?";
	private static String CREATE_FLIGHT_SQL = "insert into flight(takeOff,landing, flightLine_idflightLine, deleted) values (?,?,?,0)";
	private static String DELETE_FLIGHT_SQL = "update flight set deleted=1 where idflight=?";
	
	public boolean login(String username,String password) {
				
		
			Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(LOGIN);
			prepStatement.setString(1,username);
			prepStatement.setString(2,password);
			
			
			ResultSet resultSet = prepStatement.executeQuery();
			
		
			
			// provjeri da li korisnik ulogovan
			if (resultSet.next() ) {
				
				return true;
				
			}else {
				
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return false;
		
	}
	
	
	public ArrayList<Reservation> getReservations(String status) {
		
			Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(RESERVATIONS_SQL);
			
			prepStatement.setString(1,status);
			
			ResultSet resultSet = prepStatement.executeQuery();
			
			ArrayList<Reservation> reservations = new ArrayList<Reservation>();
			
		
			
			// pribavi rezervacije iz baze
			while(resultSet.next() ) {
				
			
				 String reservationId = resultSet.getString("id");
				 int reservationID = Integer.parseInt(reservationId);
				 
				 String statuss = resultSet.getString("status");
				 String createTime = resultSet.getString("createTime");
				 String takeOff = resultSet.getString("takeOff");
				 String flightType = resultSet.getString("flightType");
				 String destination = resultSet.getString("end_townName");
				 String name = resultSet.getString("name");
				 String surname = resultSet.getString("surname");
				 
				 reservations.add(new Reservation(reservationID,destination,takeOff, flightType,statuss, name, surname, createTime));
				
				 
			}
			
			
			return reservations;
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
		
	}
	
	
	public void acceptReservation(int id) {
		
		
			Connection con = null;
		
		try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(ACCEPT_RESERVATION_SQL);
			
			
				prepStatement.setInt(1, id);
			
				prepStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
	}
	
	
	
	public void declineReservation(int id, String reason) {
		
		
		Connection con = null;
	
	try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(DECLINE_RESERVATION_SQL);
		
			prepStatement.setString(1, reason);
			prepStatement.setInt(2, id);
		
			prepStatement.executeUpdate();

		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	
}
		
	
	public ArrayList<Message> getMessages() {
		
		Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(GET_MESSAGES_SQL);
		
		
		
		ResultSet resultSet = prepStatement.executeQuery();
		
		ArrayList<Message> messages = new ArrayList<Message>();
		
	
		
		// pribavi poruke iz baze
		while(resultSet.next() ) {
			
		
			 String messageId = resultSet.getString("id");
			 int messageID = Integer.parseInt(messageId);
			 
			 String email = resultSet.getString("email");
			 String title = resultSet.getString("title");
			 String message = resultSet.getString("message");
			 String status = resultSet.getString("status");
			 
			 
			 messages.add(new Message(messageID, email, title, message,status));
			
			 
		}
		
		
		return messages;
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
	
	
}
	
public void messageSeen(int idMessage) {
		
		
		Connection con = null;
	
	try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(MESSAGE_SEEN_SQL);
			
			prepStatement.setInt(1, idMessage);
		
			prepStatement.executeUpdate();

		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	
}


public ArrayList<Message> filterMessages(String content) {
	
	Connection con = null;

try {
	con = ConnectionPool.getConnectionPool().checkOut();
	
	PreparedStatement prepStatement = con.prepareStatement(FILTER_MESSAGE_SQL);
	
	prepStatement.setString(1, "%" + content + "%");
	
	ResultSet resultSet = prepStatement.executeQuery();
	
	ArrayList<Message> messages = new ArrayList<Message>();
	

	
	// pribavi poruke iz baze
	while(resultSet.next() ) {
		
	
		 String messageId = resultSet.getString("id");
		 int messageID = Integer.parseInt(messageId);
		 
		 String email = resultSet.getString("email");
		 String title = resultSet.getString("title");
		 String message = resultSet.getString("message");
		 String status = resultSet.getString("status");
		 
		 
		 messages.add(new Message(messageID, email, title, message,status));
		
		 
	}
	
	
	return messages;
	
} catch (Exception e) {
	e.printStackTrace();

} finally {
	ConnectionPool.getConnectionPool().checkIn(con);
}

return null;




}
		

		public void sendEmail(Message answer) {
	
			 // Recipient's email ID needs to be mentioned.
	        String to = answer.getEmail();

	        // Sender's email ID needs to be mentioned
	        String from = answer.getEmail();

	        // Assuming you are sending email from through gmails smtp
	        String host = "smtp.gmail.com";

	        // Get system properties
	        Properties properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	        // Get the Session object.// and pass username and password
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication("ristomaric100@gmail.com", "***********");

	            }

	        });

	        // Used to debug SMTP issues
	        session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject(answer.getTitle());

	            // Now set the actual message
	            message.setText(answer.getMessage());

	            System.out.println("sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
			


			}
	
		

		public ArrayList<Flight> getFlights() {
			
			Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(GET_FLIGHTS_SQL);
			
			
			
			ResultSet resultSet = prepStatement.executeQuery();
			
			ArrayList<Flight> flights = new ArrayList<Flight>();
			
		
			
			// pribavi letove iz baze
			while(resultSet.next() ) {
				
			
				 String flightId = resultSet.getString("idflight");
				 int flightID = Integer.parseInt(flightId);
				 
				 String takeOff = resultSet.getString("takeOff");
				 String landing = resultSet.getString("landing");
				 String flightType = resultSet.getString("flightType");
				 String start_townName = resultSet.getString("start_townName");
				 String start_stateName = resultSet.getString("start_stateName");
				 String end_townName = resultSet.getString("end_townName");
				 String end_stateName = resultSet.getString("end_stateName");
				 
				 Flight flight;
				 
				 
				 if(flightType.equals("Putnicki prevoz")) {
					 
					 String seatCount = resultSet.getString("seatCount");
					 int seatcount = Integer.parseInt(seatCount);
					 
					 flight = new PassengerFlight(flightID, takeOff, landing, flightType, start_townName, start_stateName, end_townName, end_stateName, seatcount);
					 
					 
				 }else {
					 
					 String cargoDescription = resultSet.getString("cargoDescription");
					 
					 flight = new TransportFlight(flightID, takeOff, landing, flightType, start_townName, start_stateName, end_townName, end_stateName, cargoDescription);

					 
				 }
				
				 
				 
				 flights.add(flight);
				
				 
			}
			
			
			return flights;
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
		
	}
		
		public ArrayList<FlightLine> getFlightLines() {
			
			Connection con = null;
			
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
				
				PreparedStatement prepStatement = con.prepareStatement(GET_FLIGHT_LINES_SQL);
				
				
				
				ResultSet resultSet = prepStatement.executeQuery();
				
				ArrayList<FlightLine> flightLines = new ArrayList<FlightLine>();
				
			
				
				// pribavi linije iz baze
				while(resultSet.next() ) {
					
				
					 String flightLineId = resultSet.getString("idflightLine");
					 int flightLineID = Integer.parseInt(flightLineId);
					 
				
					 String start_townName = resultSet.getString("townStart");
					 String end_townName = resultSet.getString("townEnd");
					 
					 
					 String flightType = resultSet.getString("flightType");
					 
					 FlightLine flightLine;
					 
					 
					 if(flightType.equals("Putnicki prevoz")) {
						 
						 String seatCount = resultSet.getString("seatCount");
						 int seatcount = Integer.parseInt(seatCount);
						 
						 flightLine = new PassengerFlightLine( start_townName, end_townName, seatcount,flightLineID);
						 
						 
					 }else {
						 
						 String cargoDescription = resultSet.getString("cargoDescription");
						 
						 flightLine = new TransportFlightLine(start_townName, end_townName, cargoDescription, flightLineID);

						 
					 }
					
					 
					 
					 flightLines.add(flightLine);
					
					 
				}
				
				
				return flightLines;
				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				ConnectionPool.getConnectionPool().checkIn(con);
			}
			
			return null;
			
			
			
		}
		
		
public ArrayList<Town> getTowns() {
			
			Connection con = null;
			
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
				
				PreparedStatement prepStatement = con.prepareStatement(GET_TOWNS_SQL);
				
				
				
				ResultSet resultSet = prepStatement.executeQuery();
				
				ArrayList<Town> towns = new ArrayList<Town>();
				
			
				
				// pribavi gradove iz baze
				while(resultSet.next() ) {
					
				
					 String townId = resultSet.getString("idtown");
					 int townID = Integer.parseInt(townId);
					 
				
					 String townName = resultSet.getString("townName");
					
					 Town town = new Town(townID, townName);
					 
					 
					towns.add(town);
					
					 
				}
				
				
				return towns;
				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				ConnectionPool.getConnectionPool().checkIn(con);
			}
			
			return null;
			
			
			
		}

		public void createPassangerFlightLine(PassengerFlightLine flightLine) {
			
			

			Connection con = null;
		
		try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(CREATE_FLIGHT_LINE_SQL);
			
			
				prepStatement.setString(1, flightLine.getFlightType());
				prepStatement.setInt(2, flightLine.getSeatCount());
				prepStatement.setString(3, null);
				
				if(flightLine.getArrivalFlag().equals("Arrival")) {
					
					prepStatement.setInt(4, flightLine.getTownId()); 
					
					prepStatement.setInt(5, 3); // destinacija je banja luka 
					
					
					
				}else {
					
					prepStatement.setInt(4, 3); // polazna lokacija je banja luka
					
					prepStatement.setInt(5, flightLine.getTownId());
					
					
				}
			
				prepStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
			
			
			
			
			return;
			
		}
		
		
public void createTransportFlightLine(TransportFlightLine flightLine) {
			
			

			Connection con = null;
		
		try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(CREATE_FLIGHT_LINE_SQL);
			
			
				prepStatement.setString(1, flightLine.getFlightType());
				prepStatement.setString(2, null);
				prepStatement.setString(3, flightLine.getCargo_description());
				
				if(flightLine.getArrivalFlag().equals("Arrival")) {
					
					prepStatement.setInt(4, flightLine.getTownId()); 
					
					prepStatement.setInt(5, 3); // destinacija je banja luka 
					
					
					
				}else {
					
					prepStatement.setInt(4, 3); // polazna lokacija je banja luka
					
					prepStatement.setInt(5, flightLine.getTownId());
					
					
				}
			
				prepStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
			
			
			
			
			return;
			
		}


			
		public void deleteFlightLine(int idFlightLine) {
			
			Connection con = null;
			
			try {
					con = ConnectionPool.getConnectionPool().checkOut();
				
					PreparedStatement prepStatement = con.prepareStatement(DELETE_FLIGHT_LINE_SQL);
					
					prepStatement.setInt(1, idFlightLine);
				
					prepStatement.executeUpdate();

				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				ConnectionPool.getConnectionPool().checkIn(con);
			}
			
			
		}
		
public void createFlight(Flight flight) {
			
			

			Connection con = null;
		
		try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(CREATE_FLIGHT_SQL);
			
			
				prepStatement.setString(1, flight.StartTime);
				prepStatement.setString(2, flight.EndTime);
				prepStatement.setInt(3, flight.getId());
				
				
			
				prepStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
			
			
			
			
			return;
			
		}

public void deleteFlight(int idFlight) {
	
	Connection con = null;
	
	try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(DELETE_FLIGHT_SQL);
			
			prepStatement.setInt(1, idFlight);
		
			prepStatement.executeUpdate();

		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	
}
		
		
}
	
	
	
	
	

