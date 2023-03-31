package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;
import dto.PassengerReservation;
import dto.TransportDepature;
import dto.TransportReservation;

public class MyTransportReservationDAO {
	
private static String SELECT = "select * from reservation inner join flights where flight_idflight=idflight AND user_id=? ORDER BY createTime";

	
	
	public static ArrayList<TransportReservation> getTransportFlightsReservations(int userId) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setInt(1, userId);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<TransportReservation> transportReservations = new ArrayList<>();
		
		
		
		// izvadi rezervacije transportnih putnika iz baze
		 while(resultSet.next() ) {
			 
			 
			 String reservationId = resultSet.getString("id");
			 int reservationID = Integer.parseInt(reservationId);
			
			 String status = resultSet.getString("status");
			 String createTime = resultSet.getString("createTime");
			 
			 
			 String departTime = resultSet.getString("takeOff");
			 String cargoDescription = resultSet.getString("cargoDescription"); 
			String destination = resultSet.getString("end_townName");
			
				
			TransportDepature transportDepature = new TransportDepature(destination,departTime,cargoDescription);
			
			TransportReservation transportReservation = new TransportReservation(createTime, status, transportDepature);
			
			transportReservation.setReservationId(reservationID);
			
			transportReservations.add(transportReservation);
			
			
			
		}
		
		return transportReservations;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}

	
	
	

}
