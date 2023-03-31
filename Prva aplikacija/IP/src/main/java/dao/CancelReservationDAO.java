package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CancelReservationDAO {
	
private static String UPDATE = "UPDATE reservation SET status='ponistena' WHERE id=?";
	
	public static void cancelReservation(int reservationId) {
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(UPDATE);
			
			prepStatement.setInt(1, reservationId);
			
			 prepStatement.executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
	}
		
	
	

}
