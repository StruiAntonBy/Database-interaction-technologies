package domain;

public class Email extends Entity{
	
	private String login;
	private Long contact_person_id;
	
	public String getLogin() {
		return login;
	}
	
	public Long getContactPersonId() {
		return contact_person_id;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public void setContactPersonId(Long contact_person_id) {
		this.contact_person_id=contact_person_id;
	}
}
