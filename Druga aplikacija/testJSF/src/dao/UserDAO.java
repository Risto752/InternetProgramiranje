package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ConnectionPool;
import bean.EmployeeBean;
import bean.UserBean;

public class UserDAO {
	
	
	private static String SELECT = "SELECT * FROM user WHERE deleted=0";
	private static String INSERT = "INSERT INTO user(`e-mail`, username, password, name, surname, address, state, user_type, deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0)";
	private static String DELETE = "UPDATE user SET deleted=1 WHERE id=?";
	private static String UPDATE = "UPDATE user SET `e-mail`=?, username=?, password=?, name=?, surname=?, address=?, state=?, user_type=? WHERE id=?";
	private static  String SELECTv2 = "SELECT * FROM user WHERE id=?";
	
	
	public static  ArrayList<UserBean> getAllUsers() {
		
		 
		Connection con = null;
	
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<UserBean> users = new ArrayList<>();
		
		
		
			// izvadi korisnike iz baze
			while(resultSet.next() ) {
			
			
				int userId = Integer.parseInt(resultSet.getString("id"));
	
				String email = resultSet.getString("e-mail");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String address = resultSet.getString("address");
				String state = resultSet.getString("state");
				String user_type = resultSet.getString("user_type");
			
			
				users.add(new UserBean(userId,email,username,password, name, surname, address, state, user_type));
			
			
			}
		
			return users;
		
		
		} catch (Exception e) {
		e.printStackTrace();

		} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
		}
	
		return null;
	
	
	}
	
	public static void addUser(String email, String username, String password, String name, String surname, String address, String state, String user_type) {
		
		
		 
			Connection con = null;
			
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
				
				PreparedStatement prepStatement = con.prepareStatement(INSERT);
				
				prepStatement.setString(1, email);
				prepStatement.setString(2, username);
				prepStatement.setString(3, password);
				prepStatement.setString(4, name);
				prepStatement.setString(5, surname);
				prepStatement.setString(6, address);
				prepStatement.setString(7, state);
				prepStatement.setString(8, user_type);
				
				prepStatement.executeUpdate();

				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				ConnectionPool.getConnectionPool().checkIn(con);
			}
			
		
	}
	
	public static void deleteUser(int userID) {
		
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(DELETE);
			
			prepStatement.setInt(1, userID);
			
			 prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}
	
	public static UserBean updateUser(int userId) {
		
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECTv2);
			
			prepStatement.setInt(1, userId);
			
			ResultSet resultSet = prepStatement.executeQuery();
		
			 resultSet.next(); 
				
				
			 int userIdd = Integer.parseInt(resultSet.getString("id"));
				
				String email = resultSet.getString("e-mail");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String address = resultSet.getString("address");
				String state = resultSet.getString("state");
				String user_type = resultSet.getString("user_type");
				
				UserBean userBean = new UserBean(userIdd,email,username,password, name, surname, address, state, user_type);
				
				
				return userBean;
				
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
	}
	
	
	public static void updateUserChosen(UserBean userBean) {
		
		
			Connection con = null;
		
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(UPDATE);
			
			
				
				prepStatement.setString(1, userBean.getEmail());
				prepStatement.setString(2, userBean.getUsername());
				prepStatement.setString(3, userBean.getPassword());
				prepStatement.setString(4, userBean.getName());
				prepStatement.setString(5, userBean.getSurname());
				prepStatement.setString(6, userBean.getAddress());
				prepStatement.setString(7, userBean.getState());
				prepStatement.setString(8, userBean.getUser_type());
				prepStatement.setInt(9, userBean.getUserId());
			
				prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}
	

	
	
	
	
	
	
	

}
