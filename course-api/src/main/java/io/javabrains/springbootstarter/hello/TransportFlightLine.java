package io.javabrains.springbootstarter.hello;

public class TransportFlightLine extends FlightLine {

	private String cargo_description;
	private String flightType = "Transportni prevoz";

	public String getCargo_description() {
		return cargo_description;
	}
	
	public TransportFlightLine() {
		
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public void setCargo_description(String cargo_description) {
		this.cargo_description = cargo_description;
	}

	public TransportFlightLine(String starting_location, String destination, String cargo_description, int id) {
		super(starting_location, destination, id);
		this.cargo_description = cargo_description;
	}
	
	
	
	
}
