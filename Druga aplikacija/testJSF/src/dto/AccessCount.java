package dto;

public class AccessCount {
	
	private String date;
	private int count;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public AccessCount(String date, int count) {
		super();
		this.date = date;
		this.count = count;
	}
	
	
	
	

}
