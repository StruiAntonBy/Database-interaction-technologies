package domain;

public class PlanedTest extends Entity{

	private Long requirement_id;
	private String description_of_the_performance;
	private String expected_result;
	private int planned_time;
	private String level_test;
	
	public void setRequirementId(Long requirement_id) {
		this.requirement_id=requirement_id;
	}
	
	public void setDescriptionOfThePerformance(String description_of_the_performance) {
		this.description_of_the_performance=description_of_the_performance;
	}
	
	public void setExpectedResult(String expected_result) {
		this.expected_result=expected_result;
	}
	
	public void setPlannedTime(int planned_time) {
		this.planned_time=planned_time;
	}
	
	public void setLevelTest(String level_test) {
		this.level_test=level_test;
	}
	
	public Long getRequirementId() {
		return requirement_id;
	}
	
	public String getDescriptionOfThePerformance() {
		return description_of_the_performance;
	}
	
	public String getExpectedResult() {
		return expected_result;
	}
	
	public int getPlannedTime() {
		return planned_time;
	}
	
	public String getLevelTest() {
		return level_test;
	}
	
}
