package dto;

public class TransportDepature extends FlightDepature {

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
	
	
	public TransportDepature(String destination, String departTime, String cargoDescription) {
		super(destination, departTime);
		this.cargoDescription = cargoDescription;
	}
	
	
	
	
	
	
}
