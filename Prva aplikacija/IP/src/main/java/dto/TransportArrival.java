package dto;

public class TransportArrival extends FlightArrival {
	
	
	private String cargoDescription;
	private String type = "Transportni prevoz";
	
	public String getCargoDescription() {
		return cargoDescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TransportArrival(String origin, String arriveTime, String cargoDescription) {
		super(origin, arriveTime);
		this.cargoDescription = cargoDescription;
	}
	
	
	
	

}
