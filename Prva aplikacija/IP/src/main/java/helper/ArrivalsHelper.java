package helper;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.UserBean;
import dao.PassengerFlightsArrivalDateDAO;
import dao.TransportFlightsArrivalDateDAO;
import dto.PassengerArrival;
import dto.TransportArrival;

public class ArrivalsHelper {

	
	public static void getArrivals(int dayOffset,UserBean userBean, HttpServletRequest request) {
		
		if(userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDateDAO.getPassengerFlightsArrivals(userBean.dayOffset);
				 request.setAttribute("passengerArrivals", passengerArrivals);
			}else {
				 ArrayList<TransportArrival> transportArrivals =  TransportFlightsArrivalDateDAO.getTransportFlightsArrivals(userBean.dayOffset);
				 request.setAttribute("transportArrivals", transportArrivals);
			}
			
			}else {
				
				ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDateDAO.getPassengerFlightsArrivals(userBean.dayOffset);
				 request.setAttribute("passengerArrivals", passengerArrivals);
				
			}
		
		
	}
	
	
}
