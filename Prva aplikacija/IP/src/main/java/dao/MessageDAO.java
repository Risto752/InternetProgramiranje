package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDAO {

private static String INSERT = "INSERT INTO message(email, title, message,status) VALUES (?,?,?,'neprocitana')";
	
	public static void insertMessage(String email, String title, String message) {
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(INSERT);
			
			
			prepStatement.setString(1, email);
			prepStatement.setString(2, title);
			prepStatement.setString(3, message);
			
			 prepStatement.executeUpdate();

		
			
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
	}
		
		
		
		
	}
	
	
	

