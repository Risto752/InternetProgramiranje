package beans;

import dao.UserDAO;

import dto.User;



public class UserBean{

	private User user = null;
	private boolean isLoggedIn = false;
	public int dayOffset = 0;

	
	

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	
	public boolean logIn(String username, String password){
		if ((user = UserDAO.logIn(username, password))!=null){
			isLoggedIn = true;
			return true;
		}
		return false;
	}
	
	public boolean register(User user) {
		
		if(UserDAO.insertUser(user)) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
		
	}
	
	
}
