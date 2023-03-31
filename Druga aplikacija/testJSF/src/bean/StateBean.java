package bean;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.EmployeeDAO;
import dao.StateDAO;

@ManagedBean(name="stateBean")
@RequestScoped
public class StateBean {
	
	
	private String stateName;
	private int stateId;
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public StateBean(String stateName, int stateId) {
		super();
		this.stateName = stateName;
		this.stateId = stateId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
	public StateBean() {
		
		
		
	}
	
	
	
	
public ArrayList<StateBean> getAllStates() {
		
		return StateDAO.getAllStates();
		
	}



public String addState() {
	
	 StateDAO.addState(stateName);
	
	return "states.xhtml?faces-redirect=true";  
	
}

public String deleteState(int stateId) {
	
	
	 StateDAO.deleteState(stateId);
	
	 return null;
	
}


public String updateState(int stateId) {
	
	
	
	StateBean state = StateDAO.updateState(stateId);
	 
	 
	 Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
		sessionMap.put("updateState", state);
	
	return "updateState.xhtml?faces-redirect=true";




}


public String updateStateChosen(StateBean stateBean) {
	
	
	StateDAO.updateStateChosen(stateBean);
	
	return "/states.xhtml?faces-redirect=true";  
	
	
}

}
