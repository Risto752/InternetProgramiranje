package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.State;
import dto.Town;

public class TownDAO {
	
	 private static String SELECT = "SELECT townName from town WHERE state_idstate=? AND deleted=0";
		
	public static ArrayList<Town> getTownsByStateId(int stateId){
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
			prepStatement.setInt(1,stateId);
			
			ResultSet resultSet = prepStatement.executeQuery();
			
			
			ArrayList<Town> towns = new ArrayList<>();
			
			// izvadi drzave iz baze
			 while(resultSet.next() ) {
				
				
				String townName = resultSet.getString("townName");
				
				towns.add(new Town(townName));
				
				
			}
			
			return towns;
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
	}

}
