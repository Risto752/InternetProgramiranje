package io.javabrains.springbootstarter.hello;

public class PassengerFlight extends Flight {
	
	
	private int seatCount;
	private String flightType = "Putnicki prevoz";

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public PassengerFlight(int id,String takeOff, String landing, String flightType, String startTown, String startState,
			String endTown, String endState, int seatCount) {
		super(id,takeOff, landing, flightType, startTown, startState, endTown, endState);
		this.seatCount = seatCount;
	}
	
	

}
