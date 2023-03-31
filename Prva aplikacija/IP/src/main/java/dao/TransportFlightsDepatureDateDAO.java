package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.TransportDepature;

public class TransportFlightsDepatureDateDAO {

	
private static String SELECT = "SELECT * FROM flights WHERE start_townName='Banja Luka' AND flightType='Transportni prevoz' AND DATE_ADD(CAST(NOW() AS Date),INTERVAL ? DAY)=CAST(takeOff AS Date) AND deleted=0 ORDER BY takeOff";

	
	
	public static ArrayList<TransportDepature> getTransportFlightsDepatures(int dayOffset) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setInt(1, dayOffset);
		
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
