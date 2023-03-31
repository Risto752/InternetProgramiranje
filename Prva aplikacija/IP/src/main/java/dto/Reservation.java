package dto;

public abstract class Reservation {
	
	
	private String createTime, status;
	private int reservationId;

	public String getCreateTime() {
		return createTime;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Reservation(String createTime, String status) {
		super();
		this.createTime = createTime;
		this.status = status;
	}
	
	

}
