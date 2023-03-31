package dto;

public class PassengerDepature extends FlightDepature {

	private int seatCount;
	private String type = "Putnicki prevoz";
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public PassengerDepature(String destination, String departTime, int seatCount) {
		super(destination, departTime);
		this.seatCount = seatCount;
	}
	
	
	
}
