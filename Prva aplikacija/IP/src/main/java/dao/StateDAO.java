package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.State;
import dto.TransportArrival;

public class StateDAO {
	
	 private static String SELECT = "SELECT * from state WHERE deleted=0";
	
	public static ArrayList<State> getAllStates(){
		
		Connection con = null;
		
		try {
			con = ConnectionPool.getConnectionPool().checkOut();
			
			PreparedStatement prepStatement = con.prepareStatement(SELECT);
			
			ResultSet resultSet = prepStatement.executeQuery();

			ArrayList<State> states = new ArrayList<>();
			
			// izvadi drzave iz baze
			 while(resultSet.next() ) {
				
				int stateId = Integer.parseInt(resultSet.getString("idstate"));
				String stateName = resultSet.getString("stateName");
				states.add(new State(stateId,stateName));
				
				
			}
			
			return states;
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionPool.getConnectionPool().checkIn(con);
		}
		
		return null;
		
		
		
	}
	
	

}
