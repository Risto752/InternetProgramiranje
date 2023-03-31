package dto;

public abstract class FlightArrival {

	private String origin, arriveTime;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public FlightArrival(String origin, String arriveTime) {
		super();
		this.origin = origin;
		this.arriveTime = arriveTime;
	}
	
}
