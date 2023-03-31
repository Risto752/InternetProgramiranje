package io.javabrains.springbootstarter.hello;

public class Town {
	
	
	private int id;
	private String townName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public Town(int id, String townName) {
		super();
		this.id = id;
		this.townName = townName;
	}
	
	
	

}
