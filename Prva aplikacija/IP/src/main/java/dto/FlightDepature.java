package dto;

public abstract class FlightDepature {
	
	
	private String destination, departTime;
	private int flightId;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartTime() {
		return departTime;
	}

	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}

	public FlightDepature(String destination, String departTime) {
		super();
		this.destination = destination;
		this.departTime = departTime;
	}

}
