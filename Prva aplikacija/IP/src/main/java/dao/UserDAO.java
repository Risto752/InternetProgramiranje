package dao;

import dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UserDAO {
	
	private static String INSERT = "INSERT INTO `user` (`e-mail`,username,password,name,surname,address,state,user_type,deleted)  VALUES (?, ?, ?, ?, ?, ?, ?, ?,0)";
	private static String SELECT = "SELECT `e-mail` FROM `user` WHERE `e-mail`=?";
	private static String LOGIN = "SELECT * FROM `user` WHERE `username`=? AND `password`=? AND deleted=0";
	private static String ACCESS_COUNT = "SELECT * FROM accesscount WHERE date=?";
	private static String INCREMENT = "UPDATE accesscount SET count = count + 1 WHERE date=?";
	private static String INSERT_DATE = "INSERT INTO accesscount(date,count) VALUES (?,1)";

	
	public static User logIn(String username, String password) {
		

		Connection con = null;
		 
		
		
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(LOGIN);
			prepStatement.setString(1,username);
			prepStatement.setString(2,password);
			
			
			ResultSet resultSet = prepStatement.executeQuery();
			
			User user = new User();
			
			// provjeri da li korisnik ulogovan
			if (resultSet.next() ) {
				
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				
				int userID = Integer.parseInt(resultSet.getString("id"));
				user.setUserID(userID);
				user.setType(resultSet.getString("user_type"));
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				   LocalDateTime now = LocalDateTime.now();  
				  String currentDate = dtf.format(now); 
				
				   prepStatement = con.prepareStatement(ACCESS_COUNT);
				  
				  prepStatement.setString(1, currentDate);
				  
				   resultSet = prepStatement.executeQuery();
				  
				  
				  
				  if(resultSet.next()) {
					  
					  prepStatement = con.prepareStatement(INCREMENT);
					  
					  prepStatement.setString(1, currentDate);
					  
					  prepStatement.executeUpdate();
					  
					  
				  }else {
					  
					  prepStatement = con.prepareStatement(INSERT_DATE);
					  
					  prepStatement.setString(1, currentDate);
					  
					  prepStatement.executeUpdate();
					  
					  
				  }
				  
				
				
				return user;
				
			}else {
				
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
	}
	
	
	
	public static boolean insertUser(User user) {
		
		
		Connection con = null;
		
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
			prepStatement.setString(1, user.getEmail());
			ResultSet resultSet = prepStatement.executeQuery();
			
			// provjeri da li je e-mail vec postojeci
			if (!resultSet.next()) {
				
				// dodaj e-mail u bazu podataka
				Object[] values = {user.getEmail(), user.getUsername(), user.getPassword(), user.getName(), user.getSurname(),
						user.getAddress(), user.getState(), user.getType()};
				
				PreparedStatement ps = DAOUtil.prepareStatement(con, INSERT, true,values);
				 ps.executeUpdate();
			    return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return false;
		
	}
	
	

	
	
	

}
