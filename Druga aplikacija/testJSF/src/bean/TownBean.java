package bean;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import dao.EmployeeDAO;
import dao.TownDAO;


@ManagedBean(name="townBean")
@RequestScoped
public class TownBean {
	
	
	private String townName, stateName;
	private int townId, stateId;
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public TownBean(String townName, String stateName, int townId, int stateId) {
		super();
		this.townName = townName;
		this.stateName = stateName;
		this.townId = townId;
		this.stateId = stateId;
	}
	public int getTownId() {
		return townId;
	}
	public void setTownId(int townId) {
		this.townId = townId;
	}
	
	public TownBean() {
		
	}
	
public ArrayList<TownBean> getAllTowns() {
		
		return TownDAO.getAllTowns();
		
	}

public String deleteTown(int townId) {
	
	
	 TownDAO.deleteTown(townId);
	
	 return null;
	
}

public String updateTown(int townId) {
	
	
	
	TownBean town = TownDAO.updateTown(townId);
	 
	 
	 Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
		sessionMap.put("updateTown", town);
	
	return "updateTown.xhtml?faces-redirect=true";




}


public String updateTownChosen(TownBean townBean) {
	
	
	TownDAO.updateTownChosen(townBean);
	
	return "/towns.xhtml?faces-redirect=true";  
	
	
}
	

public String addTown() {
	
	 TownDAO.addTown(townName, stateId);
	
	return "towns.xhtml?faces-redirect=true";  
	
}


}
