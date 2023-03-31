package io.javabrains.springbootstarter.hello;

public class Reservation {
	
	
	private String destination, takeOffTime, flightType, status, name, surname, createTime;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Reservation(int id,String destination, String takeOffTime, String flightType, String status, String name,
			String surname, String createTime) {
		super();
		this.id = id;
		this.destination = destination;
		this.takeOffTime = takeOffTime;
		this.flightType = flightType;
		this.status = status;
		this.name = name;
		this.surname = surname;
		this.createTime = createTime;
	}

	public String getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(String takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

}
