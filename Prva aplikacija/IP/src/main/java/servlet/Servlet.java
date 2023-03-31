package servlet;

import dao.CancelReservationDAO;
import dao.MessageDAO;
import dao.MyPassengerReservationDAO;
import dao.MyTransportReservationDAO;
import dao.PassengerFlightsArrivalDAO;


import dao.PassengerFlightsArrivalDateDAO;
import dao.PassengerFlightsDepatureDAO;
import dao.PassengerFlightsDepatureDateDAO;
import dao.PassengerReservationDAO;
import dao.StateDAO;
import dao.TownDAO;
import dao.TransportFlightsArrivalDAO;
import dao.TransportFlightsArrivalDateDAO;
import dao.TransportFlightsDepatureDAO;
import dao.TransportFlightsDepatureDateDAO;
import dao.TransportReservationDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;


import com.google.gson.Gson;

import beans.UserBean;
import dao.UserDAO;
import dao.makeReservationDAO;
import dao.TownDAO;
import dao.StateDAO;
import dto.PassengerArrival;
import dto.PassengerDepature;
import dto.PassengerReservation;
import dto.State;
import dto.Town;
import dto.TransportArrival;
import dto.TransportDepature;
import dto.TransportReservation;
import dto.User;
import helper.ArrivalsHelper;
import helper.DepaturesHelper;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
@MultipartConfig
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String address = "/WEB-INF/pages/startingPage.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (action == null || action.equals("")) {
			
			 address = "/WEB-INF/pages/startingPage.jsp";
			
			// pribavi odlaske
			
			// ukoliko se prvi put prijavljuje jos nije kreiran userBean
			if(session.getAttribute("userBean") == null) {
		
			 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();	
			 
			 request.setAttribute("passengerDepatures", passengerDepatures);
			 
			}else {
				
				UserBean userBean = (UserBean)session.getAttribute("userBean");
				
				// ukoliko je korisnik ulogovan provjeravamo tip korisnika
				if(userBean.isLoggedIn()) {
				
				if(userBean.getUser().getType().equals("Putnicki prevoz")) {
					 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();
					 request.setAttribute("passengerDepatures", passengerDepatures);
				}else {
					 ArrayList<TransportDepature> transportDepatures =  TransportFlightsDepatureDAO.getTransportFlightsDepatures();
					 request.setAttribute("transportDepatures", transportDepatures);
				}
				
				}else {
					
					ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();
					 request.setAttribute("passengerDepatures", passengerDepatures);
					
				}
				
			}
			 
			
			// pribavi dolaske
			 
		
			if(session.getAttribute("userBean") == null) {
				
				 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();	
				 request.setAttribute("passengerArrivals", passengerArrivals);
				 
				}else {
					
					UserBean userBean = (UserBean)session.getAttribute("userBean");
					
					if(userBean.isLoggedIn()) {
					
					if(userBean.getUser().getType().equals("Putnicki prevoz")) {
						 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();
						 request.setAttribute("passengerArrivals", passengerArrivals);
					}else {
						 ArrayList<TransportArrival> transportArrivals =  TransportFlightsArrivalDAO.getTransportFlightsArrivals();
						 request.setAttribute("transportArrivals", transportArrivals);
					}
					
					}else {
						
						ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();
						 request.setAttribute("passengerArrivals", passengerArrivals);
						
					}
					
				}
			
			
			
		}else if(action.equals("flightsArrivalJSON")){
		
			// pribavi dolaske u JSON formatu
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
				
			
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			
			if(userBean != null &&  userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();
				
			 String	passengerArrivalJSONStr =  gson.toJson(passengerArrivals);
				 
			 out.print(passengerArrivalJSONStr);
		        out.flush(); 
				 return;
				 
			}else {
				 ArrayList<TransportArrival> transportArrivals =  TransportFlightsArrivalDAO.getTransportFlightsArrivals();
				 
				 String	transportArrivalsJSONStr =  gson.toJson(transportArrivals);
				 
				 out.print(transportArrivalsJSONStr);
			        out.flush(); 
				 return;
			}
			
			}else {
				
				ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();
				
				String	passengerArrivalsJSONStr =  gson.toJson(passengerArrivals);
				 
				 out.print(passengerArrivalsJSONStr);
			        out.flush(); 
			        
			        return;
				
			}
			
			
			
			
		
		}else if(action.equals("flightsDepaturesJSON")){
		
		
			// pribavi odlaske u JSON formatu
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
				
			
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			
			if(userBean != null &&  userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();
				
			 String	passengerDepJSONStr =  gson.toJson(passengerDepatures);
				 
			 out.print(passengerDepJSONStr);
		        out.flush(); 
				 return;
				 
			}else {
				 ArrayList<TransportDepature> transportDepatures =  TransportFlightsDepatureDAO.getTransportFlightsDepatures();
				 
				 String	transportDepJSONStr =  gson.toJson(transportDepatures);
				 
				 out.print(transportDepJSONStr);
			        out.flush(); 
				 return;
			}
			
			}else {
				
				ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();
				
				String	passengerDepJSONStr =  gson.toJson(passengerDepatures);
				 
				 out.print(passengerDepJSONStr);
			        out.flush(); 
			        
			        return;
				
			}
			
		
		}else if(action.equals("allFlightsDepaturesToday")){
		
			// svi odlasci danas
			 
			 address = "/WEB-INF/pages/depatures.jsp";
					
					UserBean userBean = (UserBean)session.getAttribute("userBean");			
					userBean.dayOffset = 0;
					
					DepaturesHelper.getDepatures(userBean.dayOffset, userBean, request);
					
					session.setAttribute("userBean", userBean);
					
		
		}else if(action.equals("allFlightsDepaturesTommorow")){
			
			// svi odlasci za sutra
			
			 address = "/WEB-INF/pages/depatures.jsp";
				
				UserBean userBean = (UserBean)session.getAttribute("userBean");			
				userBean.dayOffset = 1;
				
				DepaturesHelper.getDepatures(userBean.dayOffset, userBean, request);
				
				session.setAttribute("userBean", userBean);
			
			
			
		
		}else if(action.equals("allFlightsDepaturesYesterday")){
			
			// svi odlasci za juce
			
			
			address = "/WEB-INF/pages/depatures.jsp";
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");			
			userBean.dayOffset = -1;
			
			DepaturesHelper.getDepatures(userBean.dayOffset, userBean, request);
			
			session.setAttribute("userBean", userBean);
				
			
		
		}else if(action.equals("allFlightsDepaturesJSON")){
		
		
			// pribavi odlaske u JSON formatu za odgovarajuci dan
			
			
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
				
			
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			
			if(userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDateDAO.getPassengerFlightsDepatures(userBean.dayOffset);
				
			 String	passengerDepJSONStr =  gson.toJson(passengerDepatures);
				 
			 out.print(passengerDepJSONStr);
		        out.flush(); 
				 return;
				 
			}else {
				 ArrayList<TransportDepature> transportDepatures =  TransportFlightsDepatureDateDAO.getTransportFlightsDepatures(userBean.dayOffset);
				 
				 String	transportDepJSONStr =  gson.toJson(transportDepatures);
				 
				 out.print(transportDepJSONStr);
			        out.flush(); 
				 return;
			}
			
			}else {
				
				ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDateDAO.getPassengerFlightsDepatures(userBean.dayOffset);
				
				String	passengerDepJSONStr =  gson.toJson(passengerDepatures);
				 
				 out.print(passengerDepJSONStr);
			        out.flush(); 
			        
			        return;
				
			}
			
		
		}else if(action.equals("allFlightsArrivalsToday")){
			
			// svi dolasci za danas
			
			 address = "/WEB-INF/pages/arrivals.jsp";
				
					UserBean userBean = (UserBean)session.getAttribute("userBean");
					
					userBean.dayOffset = 0;
					
					ArrivalsHelper.getArrivals(userBean.dayOffset, userBean, request);
					
					session.setAttribute("userBean", userBean);
					
		}else if(action.equals("allFlightsArrivalsTommorow")){
			
			
			// svi dolasci za sutra
			
			 address = "/WEB-INF/pages/arrivals.jsp";
				
					UserBean userBean = (UserBean)session.getAttribute("userBean");
					
					userBean.dayOffset = 1;
					
					ArrivalsHelper.getArrivals(userBean.dayOffset, userBean, request);
					
					session.setAttribute("userBean", userBean);
					
		}else if(action.equals("allFlightsArrivalsYesterday")){
			
			// svi dolasci za juce
			
			 address = "/WEB-INF/pages/arrivals.jsp";
				
					UserBean userBean = (UserBean)session.getAttribute("userBean");
					
					userBean.dayOffset = -1;
					
					ArrivalsHelper.getArrivals(userBean.dayOffset, userBean, request);
					
					session.setAttribute("userBean", userBean);
					
		}else if(action.equals("allFlightsArrivalsJSON")){
		
		
			// pribavi dolaske u JSON formatu za odgovarajuci dan
			
			
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
				
			
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			
			if(userBean.isLoggedIn()) {
			
			if(userBean.getUser().getType().equals("Putnicki prevoz")) {
				 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDateDAO.getPassengerFlightsArrivals(userBean.dayOffset);
				
			 String	passengerArrivalsJSONStr =  gson.toJson(passengerArrivals);
				 
			 out.print(passengerArrivalsJSONStr);
		        out.flush(); 
				 return;
				 
			}else {
				 ArrayList<TransportArrival> transportArrivals =  TransportFlightsArrivalDateDAO.getTransportFlightsArrivals(userBean.dayOffset);
				 
				 String	transportArrivalsJSONStr =  gson.toJson(transportArrivals);
				 
				 out.print(transportArrivalsJSONStr);
			        out.flush(); 
				 return;
			}
			
			}else {
				
				ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDateDAO.getPassengerFlightsArrivals(userBean.dayOffset);
				
				String	passengerArrivalsJSONStr =  gson.toJson(passengerArrivals);
				 
				 out.print(passengerArrivalsJSONStr);
			        out.flush(); 
			        
			        return;
				
			}
			
		
		}else if(action.equals("logout")){
			
			// logout iz aplikacije
			
			session.invalidate();
			
			address = "/WEB-INF/pages/startingPage.jsp";
			
			 ArrayList<PassengerDepature> passengerDepatures =  PassengerFlightsDepatureDAO.getPassengerFlightsDepatures();	
			 request.setAttribute("passengerDepatures", passengerDepatures);
			 
			 
			 ArrayList<PassengerArrival> passengerArrivals =  PassengerFlightsArrivalDAO.getPassengerFlightsArrivals();	
			 request.setAttribute("passengerArrivals", passengerArrivals);
			 
			 
		}
		else if(action.equals("register")) {
			
			
			// registracija
			
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String addres = request.getParameter("address");
			String state = request.getParameter("state");
			String flexRadioDefault = request.getParameter("flexRadioDefault");
			
			User user = new User(email,username,password1,name,surname,addres,state,flexRadioDefault);
			
			UserBean userBean = new UserBean();
			
			PrintWriter pw = response.getWriter();
			
			if(userBean.register(user)) {
			
				pw.println(true);
			
			}else {
				
				pw.println(false);
			
			}
			
			return;
					
		}else if(action.equals("reservation")) {
			
			
			 address = "/WEB-INF/pages/reservation.jsp";
			
			ArrayList<State> states = StateDAO.getAllStates();
			
			request.setAttribute("states", states);
			
			State state = states.get(0); // uzmi prvu drzavu iz liste
			
			ArrayList<Town> towns = TownDAO.getTownsByStateId(state.getStateId());
			
			request.setAttribute("towns", towns);
			
			
		}else if(action.equals("allreservations")) {
			
			 address = "/WEB-INF/pages/allreservations.jsp";
			
			String stateId = request.getParameter("state");
			
			
			int stateID = Integer.parseInt(stateId);
			String town = request.getParameter("town");
			String tripStart = request.getParameter("trip-start");
			
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
			
			String userType = userBean.getUser().getType();
			int userId = userBean.getUser().getUserID();
			
			
			if(userType.equals("Putnicki prevoz")) {
				
				ArrayList<PassengerDepature> passengerDepatures =  PassengerReservationDAO.getPassengerFlightsDepatures(stateID, town, tripStart,userId);
				
				request.setAttribute("passengerDepatures", passengerDepatures);
				
				
			}else {
				
				ArrayList<TransportDepature> transportDepatures =  TransportReservationDAO.getTransportFlightsDepatures(stateID, town, tripStart,userId);
				
				request.setAttribute("transportDepatures", transportDepatures);
				
				
			}
			
			
			
			
			
		
		}else if(action.equals("makereservation")) {
			
			
			String flightId = request.getParameter("flightId");
			
			
			System.out.println(flightId);
			
			int flightID = Integer.parseInt(flightId);
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
			
			int userId = userBean.getUser().getUserID();
			
			
			makeReservationDAO.makeReservation(userId, flightID);
			
			
			
			return;
		
		}else if(action.equals("myreservations")) {
			
			
			 address = "/WEB-INF/pages/myreservations.jsp";
				
			
			UserBean userBean = (UserBean)session.getAttribute("userBean");
			
			User user =  userBean.getUser();
			
			int userId = user.getUserID();
			
			if(user.getType().equals("Putnicki prevoz")) {
				
				
				ArrayList<PassengerReservation> passengerReservations = MyPassengerReservationDAO.getPassengerFlightsReservations(userId);
				
				request.setAttribute("passengerReservations", passengerReservations);
				
	
			}else {
				
				
				ArrayList<TransportReservation> transportReservations = MyTransportReservationDAO.getTransportFlightsReservations(userId);
				
				request.setAttribute("transportReservations", transportReservations);
				
			}
			
		
		}else if(action.equals("cancelReservaton")) {
			
			String reservationId = request.getParameter("reservationId");
			int reservationID = Integer.parseInt(reservationId);
			
			CancelReservationDAO.cancelReservation(reservationID);
			
			return;
		
		}else if(action.equals("message")) {
			
			 address = "/WEB-INF/pages/startingPage.jsp";
			
			String email = request.getParameter("email");
			String title = request.getParameter("title");
			String message = request.getParameter("message");
			
			
						
			MessageDAO.insertMessage(email, title, message);
			
			return;
			
		
		}else if(action.equals("townsJSON")) {
			
			
			// vracanje gradova u JSON formatu
			
	 	    int stateId = Integer.parseInt(request.getParameter("stateId"));
			
			
	 	     ArrayList<Town> towns =  TownDAO.getTownsByStateId(stateId);
	 	     
	 	    Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			String	townsJSON =  gson.toJson(towns);
			 
			 out.print(townsJSON);
		        out.flush(); 
	 	    
			
			return;
			
			
			
		}else if(action.equals("login")) {
			
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserBean userBean = new UserBean();
			
			
			
			PrintWriter pw = response.getWriter();
			
			
			
			if(userBean.logIn(username, password)) {
				
				session.setAttribute("userBean", userBean);
				
				pw.println(true);
				
			}else {
				
				pw.println(false);
			
			}
			
			return;
			
			
		}
		
			
				RequestDispatcher view = request.getRequestDispatcher(address);
				view.forward(request, response);
			
			
			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
