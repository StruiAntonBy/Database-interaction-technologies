package domain;

public class ContactPerson extends Entity{

	private String surname;
	private String name;
	private String middle_name;
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getMiddleName() {
		return middle_name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}
	
	public void setMiddleName(String middle_name) {
		this.middle_name=middle_name;
	}
}
