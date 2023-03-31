package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dto.TransportDepature;

public class makeReservationDAO {
	
	private static String INSERT = "INSERT INTO reservation(status,createTime,user_id, flight_idflight) VALUES ('nova',?,?,?)";
	
	public static void makeReservation(int userId, int flightId) {
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(INSERT);
			
			
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			   LocalDateTime now = LocalDateTime.now();
			   
			   String currentDate = dtf.format(now);
			
			prepStatement.setString(1, currentDate);
			prepStatement.setInt(2, userId);
			prepStatement.setInt(3, flightId);
			
			 prepStatement.executeUpdate();

		
			
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
	}
		
		
		
		
	}
	


