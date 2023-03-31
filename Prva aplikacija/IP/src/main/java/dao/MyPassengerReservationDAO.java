package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;
import dto.PassengerReservation;

public class MyPassengerReservationDAO {
	
	
private static String SELECT = "select * from reservation inner join flights where flight_idflight=idflight AND user_id=? ORDER BY createTime";

	
	
	public static ArrayList<PassengerReservation> getPassengerFlightsReservations(int userId) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setInt(1, userId);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<PassengerReservation> passengerReservations = new ArrayList<>();
		
		
		
		// izvadi rezervacije putnika iz baze
		 while(resultSet.next() ) {
			
			 
			 String reservationId = resultSet.getString("id");
			 int reservationID = Integer.parseInt(reservationId);
			 
			 String status = resultSet.getString("status");
			 String createTime = resultSet.getString("createTime");
			 
			 
			 String departTime = resultSet.getString("takeOff");
			 String seatCountt = resultSet.getString("seatCount");
			 int seatCount = Integer.parseInt(seatCountt);
			 
			String destination = resultSet.getString("end_townName");
			
				
			PassengerDepature passengerDepature = new PassengerDepature(destination,departTime,seatCount);
			
			PassengerReservation passengerReservation = new PassengerReservation(createTime, status, passengerDepature);
			
			passengerReservation.setReservationId(reservationID);
			
			
			passengerReservations.add(passengerReservation);
			
			
			
		}
		
		return passengerReservations;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}



}
