package io.javabrains.springbootstarter.hello;

public class PassengerFlightLine extends FlightLine {
	
	
	private int seatCount;
	private String flightType = "Putnicki prevoz";
	
	

	public String getFlightType() {
		return flightType;
	}
	
	public PassengerFlightLine() {
		
		
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public PassengerFlightLine(String starting_location, String destination, int seatCount, int id) {
		super(starting_location, destination,id);
		this.seatCount = seatCount;
	}
	
	
	

}
