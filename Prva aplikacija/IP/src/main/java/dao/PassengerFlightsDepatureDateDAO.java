package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;

public class PassengerFlightsDepatureDateDAO {
	
	
	private static String SELECT = "SELECT * FROM flights WHERE start_townName='Banja Luka' AND flightType='Putnicki prevoz' AND DATE_ADD(CAST(NOW() AS Date),INTERVAL ? DAY)=CAST(takeOff AS Date) AND deleted=0 ORDER BY takeOff";

	
	
	public static ArrayList<PassengerDepature> getPassengerFlightsDepatures(int dayOffset) {
	

	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
		prepStatement.setInt(1, dayOffset);
		
		ResultSet resultSet = prepStatement.executeQuery();

		ArrayList<PassengerDepature> passengerDepatures = new ArrayList<>();
		
		
		
		// izvadi odlaske iz baze
		 while(resultSet.next() ) {
			
			
			String destination = resultSet.getString("end_townName");
			String departTime = resultSet.getString("takeOff");
			int seatCount = Integer.parseInt(resultSet.getString("seatCount"));
			
			passengerDepatures.add(new PassengerDepature(destination,departTime,seatCount));
			
			
		}
		
		return passengerDepatures;
		
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
}


}
