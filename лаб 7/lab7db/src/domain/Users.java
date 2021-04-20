package domain;

public class Users extends Entity{
	
	private String login;
	private String password;
	private int role;
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setRole(int role) {
		this.role=role;
	}
}
