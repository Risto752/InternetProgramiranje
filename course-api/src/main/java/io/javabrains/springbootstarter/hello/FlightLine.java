package io.javabrains.springbootstarter.hello;

public class FlightLine {
	
	private int id, townId;
	private String starting_location, destination, arrivalFlag;
	
	public FlightLine() {
		
		
	}
	

	public String getArrivalFlag() {
		return arrivalFlag;
	}


	public void setArrivalFlag(String arrivalFlag) {
		this.arrivalFlag = arrivalFlag;
	}


	public int getTownId() {
		return townId;
	}

	public void setTownId(int townId) {
		this.townId = townId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStarting_location() {
		return starting_location;
	}

	public void setStarting_location(String starting_location) {
		this.starting_location = starting_location;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public FlightLine(String starting_location, String destination, int id) {
		super();
		this.starting_location = starting_location;
		this.destination = destination;
		this.id = id;
	}

	
	
	
	

}
