package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.TransportArrival;
import dto.TransportDepature;

public class TransportFlightsArrivalDAO {

	
private static String SELECT = "SELECT * FROM flights WHERE end_townName='Banja Luka' AND flightType='Transportni prevoz' AND landing > NOW() AND deleted=0 ORDER BY landing  LIMIT 5";

	
	
	public static ArrayList<TransportArrival> getTransportFlightsArrivals() {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<TransportArrival> transportArrivals = new ArrayList<>();
		
		
		
		// izvadi odlaske iz baze
		 while(resultSet.next() ) {
			
			
			String origin = resultSet.getString("start_townName");
			String landingTime = resultSet.getString("landing");
			String cargoDescription = resultSet.getString("cargoDescription");
			
			transportArrivals.add(new TransportArrival(origin,landingTime,cargoDescription));
			
			
		}
		
		return transportArrivals;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}
	
	
	
}
