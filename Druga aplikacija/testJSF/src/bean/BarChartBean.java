package bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import dao.AccessDAO;
import dto.AccessCount;

@ManagedBean(name="barChartBean")
@RequestScoped
public class BarChartBean {
	
	
	private BarChartModel barModel;
	
	@PostConstruct
	public void init() {
		
		createBarModel();
		
	}
	
	
	private void createBarModel() {
		
		barModel = initBarModel();
		
		barModel.setTitle("Statistics");
		barModel.setLegendPosition("ne");
		
		
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Date");
		
		
		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Acces count");
		
		yAxis.setMin(0);
		yAxis.setMax(100);
		
		
	}
	
	
	
	public BarChartModel getBarModel() {
		return barModel;
	}


	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}


	private BarChartModel initBarModel() {
		
		
		BarChartModel model = new BarChartModel();
		
		
		ChartSeries accessCount = new ChartSeries();
		
		accessCount.setLabel("User activity");
		
		
		ArrayList<AccessCount> statistics = AccessDAO.getStatistics();
		
		
		for(AccessCount access : statistics) {
			
			accessCount.set(access.getDate(), access.getCount());
			
		}
		
		model.addSeries(accessCount);
		
		
		return model;
		
		
	}

}
