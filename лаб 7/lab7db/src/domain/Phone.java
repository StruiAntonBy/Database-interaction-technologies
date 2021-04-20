package domain;

public class Phone extends Entity{

	private String number;
	private Long contact_person_id;
	
	public String getNumber() {
		return number;
	}
	
	public Long getContactPersonId() {
		return contact_person_id;
	}
	
	public void setNumber(String number) {
		this.number=number;
	}
	
	public void setContactPersonId(Long contact_person_id) {
		this.contact_person_id=contact_person_id;
	}
}
