package dto;

public class TransportReservation extends Reservation {
	
	
	private TransportDepature transportDepature = null;

	public TransportDepature getTransportDepature() {
		return transportDepature;
	}

	public void setTransportDepature(TransportDepature transportDepature) {
		this.transportDepature = transportDepature;
	}

	public TransportReservation(String createTime, String status, TransportDepature transportDepature) {
		super(createTime, status);
		this.transportDepature = transportDepature;
	}
	
	

}
