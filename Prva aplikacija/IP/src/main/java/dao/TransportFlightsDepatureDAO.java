package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;
import dto.TransportDepature;

public class TransportFlightsDepatureDAO {

	private static String SELECT = "SELECT * FROM flights WHERE start_townName='Banja Luka' AND flightType='Transportni prevoz' AND takeOff > NOW() AND deleted=0 ORDER BY takeOff  LIMIT 5";

	
	
	public static ArrayList<TransportDepature> getTransportFlightsDepatures() {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<TransportDepature> transportDepatures = new ArrayList<>();
		
		
		
		// izvadi odlaske iz baze
		 while(resultSet.next() ) {
			
			
			String destination = resultSet.getString("end_townName");
			String departTime = resultSet.getString("takeOff");
			String cargoDescription = resultSet.getString("cargoDescription");
			
			transportDepatures.add(new TransportDepature(destination,departTime,cargoDescription));
			
			
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
