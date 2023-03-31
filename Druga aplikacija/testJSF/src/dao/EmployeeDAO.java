package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.context.FacesContext;

import bean.ConnectionPool;
import bean.EmployeeBean;

public class EmployeeDAO {
	
	
	private static String SELECT = "SELECT * FROM employee";
	private static String INSERT = "INSERT INTO employee(username, password) VALUES (?, ?)";
	private static String DELETE = "DELETE FROM employee WHERE idemployee=?";
	private static String UPDATE = "UPDATE employee SET username=?, password=? WHERE idemployee=?";
	private static  String SELECTv2 = "SELECT * FROM employee WHERE idemployee=?";
	
	
	public static  ArrayList<EmployeeBean> getAllEmployee() {
		
		 
		Connection con = null;
	
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<EmployeeBean> employees = new ArrayList<>();
		
		
		
			// izvadi zaposlene iz baze
			while(resultSet.next() ) {
			
			
				int employeeId = Integer.parseInt(resultSet.getString("idemployee"));
	
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
			
			
				employees.add(new EmployeeBean(username,password, employeeId));
			
			
			}
		
			return employees;
		
		
		} catch (Exception e) {
		e.printStackTrace();

		} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
		}
	
		return null;
	
	
	}
	
	public static void addEmployee(String username, String password) {
		
		
		 
			Connection con = null;
			
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
				
				PreparedStatement prepStatement = con.prepareStatement(INSERT);
				
				prepStatement.setString(1, username);
				prepStatement.setString(2, password);
				
				prepStatement.executeUpdate();

				
			} catch (Exception e) {
				e.printStackTrace();

			} finally {
				ConnectionPool.getConnectionPool().checkIn(con);
			}
			
		
	}
	
	public static void deleteEmployee(int employeeID) {
		
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(DELETE);
			
			prepStatement.setInt(1, employeeID);
			
			 prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}
	
	public static EmployeeBean updateEmployee(int employeeId) {
		
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECTv2);
			
			prepStatement.setInt(1, employeeId);
			
			ResultSet resultSet = prepStatement.executeQuery();
		
			 resultSet.next(); 
				
				
				 int employeeIdd = Integer.parseInt(resultSet.getString("idemployee"));
		
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				
				EmployeeBean employee = new EmployeeBean(username,password, employeeIdd);
				
				
				return employee;
				
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
	}
	
	
	public static void updateEmployeeChosen(EmployeeBean employeeBean) {
		
		
			Connection con = null;
		
			try {
				con = ConnectionPool.getConnectionPool().checkOut();
			
				PreparedStatement prepStatement = con.prepareStatement(UPDATE);
			
				prepStatement.setString(1, employeeBean.getUsername());
				prepStatement.setString(2, employeeBean.getPassword());
				prepStatement.setInt(3, employeeBean.getEmployeeId());
			
				prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}
	

	
	
	

}
