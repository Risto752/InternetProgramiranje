package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ConnectionPool;
import bean.EmployeeBean;
import bean.StateBean;

public class StateDAO {
	
	
	
	private static String SELECT = "SELECT * FROM state WHERE deleted=0";
	private static String INSERT = "INSERT INTO state(stateName, deleted) VALUES (?, 0)";
	private static String DELETE = "UPDATE state SET deleted=1 WHERE idstate=?";
	private static String UPDATE = "UPDATE state SET stateName=? WHERE idstate=?";
	private static  String SELECTv2 = "SELECT * FROM state WHERE idstate=?";
	
	
	public static  ArrayList<StateBean> getAllStates() {
		
		 
		Connection con = null;
	
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<StateBean> states = new ArrayList<>();
		
		
		
			// izvadi drzave iz baze
			while(resultSet.next() ) {
			
			
				int stateId = Integer.parseInt(resultSet.getString("idstate"));
	
				String stateName = resultSet.getString("stateName");
				
			
			
				states.add(new StateBean(stateName, stateId));
			
			
			}
		
			return states;
		
		
		} catch (Exception e) {
		e.printStackTrace();

		} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
		}
	
		return null;
	
	
	}
	
	
	public static void addState(String stateName) {
		
		
		 
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(INSERT);
			
			prepStatement.setString(1, stateName);
		
			
			prepStatement.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
	
}
	
	
public static void deleteState(int stateID) {
		
		

	
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(DELETE);
			
			prepStatement.setInt(1, stateID);
			
			 prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}

public static StateBean updateState(int stateId) {
	
	
	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECTv2);
		
		prepStatement.setInt(1, stateId);
		
		ResultSet resultSet = prepStatement.executeQuery();
	
		 resultSet.next(); 
			
			
			 int stateIdd = Integer.parseInt(resultSet.getString("idstate"));
	
			String stateName = resultSet.getString("stateName");
			
			
			StateBean state = new StateBean(stateName, stateIdd);
			
			
			return state;
			
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
	
}


public static void updateStateChosen(StateBean stateBean) {
	
	
	Connection con = null;

	try {
		con = ConnectionPool.getConnectionPool().checkOut();
	
		PreparedStatement prepStatement = con.prepareStatement(UPDATE);
	
		prepStatement.setString(1, stateBean.getStateName());
		prepStatement.setInt(2, stateBean.getStateId());
	
		prepStatement.executeUpdate();
	
} catch (Exception e) {
	e.printStackTrace();

} finally {
	ConnectionPool.getConnectionPool().checkIn(con);
}



}

	
	

}
