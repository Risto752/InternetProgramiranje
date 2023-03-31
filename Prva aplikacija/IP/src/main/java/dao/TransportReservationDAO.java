package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;
import dto.TransportDepature;

public class TransportReservationDAO {
	
private static String SELECT = "SELECT * FROM flights WHERE start_townName='Banja Luka' AND flightType='Transportni prevoz' AND CAST(takeOff AS Date)=? AND end_idstate=? AND end_townName=? AND NOT EXISTS(SELECT flight_idflight FROM reservation WHERE flight_idflight=idflight AND user_id=?) ORDER BY takeOff";

	
	
	public static ArrayList<TransportDepature> getTransportFlightsDepatures(int stateId, String townName, String tripStart, int userId) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setString(1, tripStart);
		prepStatement.setInt(2, stateId);
		prepStatement.setString(3, townName);
		prepStatement.setInt(4, userId);
		
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<TransportDepature> transportDepatures = new ArrayList<>();
		
		
		
		// izvadi odlaske iz baze
		 while(resultSet.next() ) {
			
			 String flightId = resultSet.getString("idflight");
			 int flightID = Integer.parseInt(flightId);
			 
			 
			String destination = resultSet.getString("end_townName");
			String departTime = resultSet.getString("takeOff");
			String cargoDescription = resultSet.getString("cargoDescription");
			
			TransportDepature transportDepature = new TransportDepature(destination,departTime,cargoDescription);
			
			transportDepature.setFlightId(flightID);
			
			transportDepatures.add(transportDepature);
			
			
		}
		
		return transportDepatures;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}



}
