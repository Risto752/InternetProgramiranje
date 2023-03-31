package bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;

import dao.EmployeeDAO;
import dao.UserDAO;

@ManagedBean(name="userBean")
@RequestScoped
public class UserBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;
	
	private String email, username, password, name, surname, address, state, user_type;
	

	private static Map<String, Object> stateValue;
	
	
	static {
		
		stateValue = new LinkedHashMap<>();
		
		
		try {

            URL url = new URL("https://restcountries.eu/rest/v2/region/europe");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
          
                JSONArray statesJson = new JSONArray(br.readLine()); 
                     
                for(int i = 0; i < statesJson.length(); i++) {
                	
                	JSONObject stateJson = statesJson.getJSONObject(i);	
                	
                	stateValue.put(stateJson.getString("name"),stateJson.getString("name"));
                	
                	
                }
                
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    
		
		
	
	}
	
	
	
	
	
	public  Map<String, Object> getStateValue() {
		return stateValue;
	}

	public  void setStateValue(Map<String, Object> statesValue) {
		UserBean.stateValue = statesValue;
	}

	public UserBean() {
		
	}

	public UserBean(int userId, String email, String username, String password, String name, String surname,
			String address, String state, String user_type) {
		super();
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.state = state;
		this.user_type = user_type;
	
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	public ArrayList<UserBean> getAllUsers() {
		
		return UserDAO.getAllUsers();
		
	}
	
	public String deleteUser(int userId) {
		
		
		 UserDAO.deleteUser(userId);
		
		 return null;
		
	}
	
	public String addUser() {
		
		 UserDAO.addUser(email, username, password, name, surname, address, state, user_type);
		
		return "users.xhtml?faces-redirect=true";  
		
	}
	
	
	public String updateUser(int userId) {
		
		
		
		UserBean user = UserDAO.updateUser(userId);
		 
		 
		 Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
			sessionMap.put("updateUser", user);
		
		return "updateUser.xhtml?faces-redirect=true";

	
	
	
}
	
	public String updateUserChosen(UserBean userBean) {
		
		
		UserDAO.updateUserChosen(userBean);
		
		return "/users.xhtml?faces-redirect=true";  
		
		
	}
	
	

	
	

}
