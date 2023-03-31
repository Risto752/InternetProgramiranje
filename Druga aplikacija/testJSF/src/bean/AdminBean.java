package bean;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;




@ManagedBean(name="adminBean")
@RequestScoped
public class AdminBean {

	private String username, password;
	private static String LOGIN = "SELECT * FROM `admin` WHERE `username`=? AND `password`=?";

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String login() {
		
		
	Connection con = null;
		 
		
		
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(LOGIN);
			prepStatement.setString(1,username);
			prepStatement.setString(2,password);
			
			
			ResultSet resultSet = prepStatement.executeQuery();
			
		
			
			// provjeri da li korisnik ulogovan
			if (resultSet.next() ) {
				
				return "/employee.xhtml?faces-redirect=true";  
				
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
	

	
	
	
	
}
