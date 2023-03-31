package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.EmployeeDAO;



@ManagedBean(name="employeeBean")
@RequestScoped
public class EmployeeBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username, password;
	private int employeeId;
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeBean(String username, String password, int employeeId) {
		super();
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
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

	public EmployeeBean() {
		
	}
	
	
	public ArrayList<EmployeeBean> getAllEmployee() {
		
		return EmployeeDAO.getAllEmployee();
		
	}
	
	
	
	public String addEmployee() {
		
		 EmployeeDAO.addEmployee(username, password);
		
		return "employee.xhtml?faces-redirect=true";  
		
	}
	
	public String deleteEmployee(int employeeId) {
		
		
		 EmployeeDAO.deleteEmployee(employeeId);
		
		 return null;
		
	}

	
	public String updateEmployee(int employeeId) {
		
		
		
			EmployeeBean employee = EmployeeDAO.updateEmployee(employeeId);
			 
			 
			 Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
				sessionMap.put("updateEmployee", employee);
			
			return "updateEmployee.xhtml?faces-redirect=true";
	
		
		
		
	}
	
	
	public String updateEmployeeChosen(EmployeeBean employeeBean) {
		
		
		EmployeeDAO.updateEmployeeChosen(employeeBean);
		
		return "/employee.xhtml?faces-redirect=true";  
		
		
	}
	
	

	
	
	
	
	

}
