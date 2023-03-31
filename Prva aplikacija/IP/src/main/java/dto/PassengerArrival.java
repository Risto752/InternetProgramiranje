package dto;

public class PassengerArrival extends FlightArrival{

	private int seatCount;
	private String type = "Putnicki prevoz";
	
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PassengerArrival(String origin, String arriveTime, int seatCount) {
		super(origin, arriveTime);
		this.seatCount = seatCount;
	}
	
	
	
	
}
