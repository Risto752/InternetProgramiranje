package dto;

public class PassengerReservation extends Reservation {
	
	
	private PassengerDepature passengerDepature = null;

	public PassengerDepature getPassengerDepature() {
		return passengerDepature;
	}

	public void setPassengerDepature(PassengerDepature passengerDepature) {
		this.passengerDepature = passengerDepature;
	}

	public PassengerReservation(String createTime, String status, PassengerDepature passengerDepature) {
		super(createTime, status);
		this.passengerDepature = passengerDepature;
	}
	
	
	
	
	

}
