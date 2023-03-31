package io.javabrains.springbootstarter.hello;

public class Flight {
	
	public String StartTime, EndTime,flighTType, startTown, startState, endTown, endState;
	public String Subject;
	private int id;

	public Flight() {
		
		
	}
	

	public Flight(int id,String takeOff, String landing, String flightType, String startTown, String startState,
			String endTown, String endState) {
		super();
		this.setId(id);
		this.StartTime = takeOff;
		this.EndTime = landing;
		this.flighTType = flightType;
		this.startTown = startTown;
		this.startState = startState;
		this.endTown = endTown;
		this.endState = endState;
		
		
		this.Subject = startTown + "(" + startState + ")" + " - " + endTown + "(" + endState + ")";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

		
	

}
