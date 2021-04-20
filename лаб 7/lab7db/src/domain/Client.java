package domain;

public class Client extends Entity{
	
	private String name;
	private String registered_address;
	private String bank_details;
	
	public String getName() {
		return name;
	}
	
	public String getRegisteredAddress() {
		return registered_address;
	}
	
	public String getBankDetails() {
		return bank_details;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setRegisteredAddress(String registered_address) {
		this.registered_address=registered_address;
	}
	
	public void setBankDetails(String bank_details) {
		this.bank_details=bank_details;
	}
}
