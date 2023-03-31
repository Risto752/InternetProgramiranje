package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerArrival;

public class PassengerFlightsArrivalDateDAO {
	
	
	
private static String SELECT = "SELECT * FROM flights WHERE end_townName='Banja Luka' AND flightType='Putnicki prevoz' AND DATE_ADD(CAST(NOW() AS Date),INTERVAL ? DAY)=CAST(landing AS Date) AND deleted=0 ORDER BY landing";

	
	
	public static ArrayList<PassengerArrival> getPassengerFlightsArrivals(int dayOffset) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setInt(1, dayOffset);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<PassengerArrival> passengerArrivals = new ArrayList<>();
		
		
		
		// izvadi odlaske iz baze
		 while(resultSet.next() ) {
			
			
			String origin = resultSet.getString("start_townName");
			String landingTime = resultSet.getString("landing");
			int seatCount = Integer.parseInt(resultSet.getString("seatCount"));
			
			passengerArrivals.add(new PassengerArrival(origin,landingTime,seatCount));
			
			
		}
		
		return passengerArrivals;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}
	

}
