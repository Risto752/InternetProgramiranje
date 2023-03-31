package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ConnectionPool;
import bean.TownBean;

public class TownDAO {
	
	
	private static String SELECT = "select * from town inner join state on state_idstate=idstate where town.deleted=0;";
	private static String INSERT = "INSERT INTO town(townName, state_idstate, deleted) VALUES (?, ?, 0)";
	private static String DELETE = "UPDATE town SET deleted=1 WHERE idtown=?";
	private static String UPDATE = "UPDATE town SET townName=? WHERE idtown=?";
	private static  String SELECTv2 = "select * from town inner join state on state_idstate=idstate where town.deleted=0 and idtown=?";
	
	
	
	public static  ArrayList<TownBean> getAllTowns() {
		
		 
		Connection con = null;
	
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
		
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
		
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<TownBean> towns = new ArrayList<>();
		
		
		
			// izvadi gradove iz baze
			while(resultSet.next() ) {
			
			
				int townId = Integer.parseInt(resultSet.getString("idtown"));
				int stateId = Integer.parseInt(resultSet.getString("state_idstate"));
	
				String townName = resultSet.getString("townName");
				String stateName = resultSet.getString("stateName");
			
			
				towns.add(new TownBean(townName,stateName, townId,stateId));
			
			
			}
		
			return towns;
		
		
		} catch (Exception e) {
		e.printStackTrace();

		} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
		}
	
		return null;
	
	
	}
	
	public static void addTown(String townName, int stateId) {
		
		
		 
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(INSERT);
			
			prepStatement.setString(1, townName);
			prepStatement.setInt(2, stateId);
			
			prepStatement.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
	
}
	
public static void deleteTown(int townID) {
		
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(DELETE);
			
			prepStatement.setInt(1, townID);
			
			 prepStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		
		
	}


public static TownBean updateTown(int townId) {
	
	
	Connection con = null;
	
	try {
		con = ConnectionPool.getConnectionPool().checkOut();
		
		PreparedStatement prepStatement = con.prepareStatement(SELECTv2);
		
		prepStatement.setInt(1, townId);
		
		ResultSet resultSet = prepStatement.executeQuery();
	
		 resultSet.next(); 
			
			
		 int townIdd = Integer.parseInt(resultSet.getString("idtown"));
			int stateId = Integer.parseInt(resultSet.getString("state_idstate"));

			String townName = resultSet.getString("townName");
			String stateName = resultSet.getString("stateName");
		
		
			TownBean town =  new TownBean(townName,stateName, townIdd,stateId);
			
			
			return town;
			
		
	} catch (Exception e) {
		e.printStackTrace();

	} finally {
		ConnectionPool.getConnectionPool().checkIn(con);
	}
	
	return null;
	
	
	
}

public static void updateTownChosen(TownBean townBean) {
	
	
	Connection con = null;

	try {
		con = ConnectionPool.getConnectionPool().checkOut();
	
		PreparedStatement prepStatement = con.prepareStatement(UPDATE);
	
		prepStatement.setString(1, townBean.getTownName());
		prepStatement.setInt(2, townBean.getTownId());
	
		prepStatement.executeUpdate();
	
} catch (Exception e) {
	e.printStackTrace();

} finally {
	ConnectionPool.getConnectionPool().checkIn(con);
}



}
	

}
