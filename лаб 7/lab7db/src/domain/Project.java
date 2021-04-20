package domain;

public class Project extends Entity{

	private String start_date;
	private String end_date;
	private Long client_id;
	
	public String getStartDate() {
		return start_date;
	}
	
	public String getEndDate() {
		return end_date;
	}
	
	public Long getClientId() {
		return client_id;
	}
	
	public void setStartDate(String start_date) {
		this.start_date=start_date;
	}
	
	public void setEndDate(String end_date) {
		this.end_date=end_date;
	}
	
	public void setClientId(Long client_id) {
		this.client_id=client_id;
	}
}
