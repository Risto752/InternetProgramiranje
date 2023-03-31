package dto;

public class State {
	
	private String name;
	private int stateId;

	public State(int stateId, String name) {
		super();
		this.name = name;
		this.stateId = stateId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public State(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
