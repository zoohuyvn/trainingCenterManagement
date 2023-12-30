package model;

/**
 * @author zoohuy
 * 28 thg 12, 2023
 */

public class user {
	private String email, password;
	private int role;
	
	public user(String email, String password, int role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getRole() {
		return role;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(int role) {
		this.role = role;
	}

	public String toString() {
		return "email=" + email + ", password=" + password + ", role=" + role;
	}
	
}