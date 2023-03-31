package io.javabrains.springbootstarter.hello;

public class Message {
	
	private int id;
	private String email, title , message, status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Message(int id, String email, String title, String message, String status) {
		super();
		this.id = id;
		this.email = email;
		this.title = title;
		this.message = message;
		this.status = status;
	}
	
	public Message() {
		
	}
	

}
