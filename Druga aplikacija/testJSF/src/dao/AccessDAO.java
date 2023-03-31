package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ConnectionPool;

import dto.AccessCount;

public class AccessDAO {
	
	
	private static String SELECT = "SELECT * FROM accesscount WHERE DATEDIFF(NOW(),accesscount.date) between 0 and 30 order by `date`";
	
	
	public static ArrayList<AccessCount> getStatistics(){
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<AccessCount> statistics = new ArrayList<>();
		
		
		
			// izvadi statistiku iz baze
			while(resultSet.next() ) {
			
			
				int count = Integer.parseInt(resultSet.getString("count"));
	
				String date = resultSet.getString("date");
	
			
			
				statistics.add(new AccessCount(date,count));
			
			
			}
		
			return statistics;
		
		
		} catch (Exception e) {
		e.printStackTrace();

		} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
		}
	
		return null;
	
	
		
		
		
	}
	
	

}
