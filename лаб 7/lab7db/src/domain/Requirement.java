package domain;

public class Requirement extends Entity{
	
	private Long project_id;
	private String requirement;
	private String start_date;
	private int planned_time;
	private String the_priority_of;
	private String level_of_criticality_for_the_client;
	private String a_mark_of_completion;
	private String the_probability_of_a_change;
	
	public void setTheProbabilityOfChange(String the_probability_of_a_change) {
		this.the_probability_of_a_change=the_probability_of_a_change;
	}
	
	public void setMarkOfCompletion(String a_mark_of_completion) {
		this.a_mark_of_completion=a_mark_of_completion;
	}
	
	public void setLevelOfCriticalityForTheClient(String level_of_criticality_for_the_client) {
		this.level_of_criticality_for_the_client=level_of_criticality_for_the_client;
	}
	
	public void setThePriorityOf(String the_priority_of) {
		this.the_priority_of=the_priority_of;
	}
	
	public void setPlannedTime(int planned_time) {
		this.planned_time=planned_time;
	}
	
	public void setStartDate(String start_date) {
		this.start_date=start_date;
	}
	
	public void setProjectId(Long project_id) {
		this.project_id=project_id;
	}
	
	public Long getProjectId() {
		return project_id;
	}
	
	public String getRequirement() {
		return requirement;
	}
	
	public String getStartDate() {
		return start_date;
	}
	
	public int getPlannedTime() {
		return planned_time;
	}
	
	public String getThePriorityOf() {
		return the_priority_of;
	}
	
	public String getLevelOfCriticalityForTheClient() {
		return level_of_criticality_for_the_client;
	}
	
	public String getMarkOfCompletion() {
		return a_mark_of_completion;
	}
	
	public String getTheProbabilityOfChange() {
		return the_probability_of_a_change;
	}

	public void setRequirement(String requirement) {
		this.requirement=requirement;
	}
}
