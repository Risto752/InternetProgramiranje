package dto;

public class User {
	
	private int userID;
	private String email,username,password,name,surname,address,state,type;

	public User() {
		
	}
	
	
	public User(String email, String username, String password, String name, String surname, String address,
			String state, String type) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.state = state;
		this.type = type;
	}
	
	
	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	


	
	

}
