package io.javabrains.springbootstarter.hello;

public class TransportFlight extends Flight {
	
	
	
	private String cargoDescription;

	public String getCargoDescription() {
		return cargoDescription;
	}

	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}

	public TransportFlight(int id, String takeOff, String landing, String flightType, String startTown, String startState,
			String endTown, String endState, String cargoDescription) {
		super(id,takeOff, landing, flightType, startTown, startState, endTown, endState);
		this.cargoDescription = cargoDescription;
	}
	
	
	

}
