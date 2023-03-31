package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.PassengerDepature;

public  class PassengerFlightsDepatureDAO {
	
	private static String SELECT = "SELECT * FROM flights WHERE start_townName='Banja Luka' AND flightType='Putnicki prevoz' AND takeOff > NOW() AND deleted=0 ORDER BY takeOff  LIMIT 5";

	
	
		public static ArrayList<PassengerDepature> getPassengerFlightsDepatures() {
		

		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
			
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
