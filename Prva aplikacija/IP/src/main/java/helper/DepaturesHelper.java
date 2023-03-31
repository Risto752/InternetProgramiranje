package helper;

import java.util.ArrayList;

import beans.UserBean;
import dao.PassengerFlightsDepatureDateDAO;
import dao.TransportFlightsDepatureDateDAO;
import dto.PassengerDepature;
import dto.TransportDepature;
import javax.servlet.http.HttpServletRequest;

public class DepaturesHelper {
	
	
	
	public static void getDepatures(int dayOffset, UserBean userBean, HttpServletRequest request) {
		
		
		if(userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDateDAO.getPassengerFlightsDepatures(userBean.dayOffset);
				 request.setAttribute("passengerDepatures", passengerDepatures);
			}else {
				 ArrayList<TransportDepature> transportDepatures =  TransportFlightsDepatureDateDAO.getTransportFlightsDepatures(userBean.dayOffset);
				 request.setAttribute("transportDepatures", transportDepatures);
			}
			
			}else {
				
				ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDateDAO.getPassengerFlightsDepatures(userBean.dayOffset);
				 request.setAttribute("passengerDepatures", passengerDepatures);
				
			}
		
		
		
		
	}
	

}
