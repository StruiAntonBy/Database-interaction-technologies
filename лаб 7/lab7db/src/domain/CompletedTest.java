package domain;

public class CompletedTest extends Entity{
	
	private Long tester_id;
	private Long planed_test_id;
	private String start_date_and_time;
	private int length;
	private String result;
	
	public void setTesterId(Long tester_id) {
		this.tester_id=tester_id;
	}
	
	public void setPlanedTestId(Long planed_test_id) {
		this.planed_test_id=planed_test_id;
	}
	
	public void setStartDateAndTime(String start_date_and_time) {
		this.start_date_and_time=start_date_and_time;
	}
	
	public void setLength(int length) {
		this.length=length;
	}
	
	public void setResult(String result) {
		this.result=result;
	}
	
	public Long getTesterId() {
		return tester_id;
	}
	
	public Long getPlanedTestId() {
		return planed_test_id;
	}
	
	public String getStartDateAndTime() {
		return start_date_and_time;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getResult() {
		return result;
	}
}
