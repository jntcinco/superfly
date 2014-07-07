package com.tekusource.superfly.model;


public class UserSession extends UserDetail {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9056502581361626607L;
	
	private String confirmPassword;
	
	public UserSession() {
	}
	
	public UserSession(UserDetail userDetails) {
		super.setUsername(userDetails.getUsername());
		super.setPassword(userDetails.getPassword());
		super.setEmail(userDetails.getEmail());
		super.setLastname(userDetails.getLastname());
		super.setFirstname(userDetails.getFirstname());
	}

	@Override
	public String getFirstname() {
		return super.getFirstname();
	}
	
	@Override
	public void setFirstname(String firstname) {
		super.setFirstname(firstname);
	}
	
	@Override
	public String getLastname() {
		return super.getLastname();
	}
	
	@Override
	public void setLastname(String lastname) {
		super.setLastname(lastname);
	}
	
	@Override
	public String getEmail() {
		return super.getEmail();
	}
	
	@Override
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	@Override
	public String getUsername() {
		return super.getUsername();
	}
	
	@Override
	public void setUsername(String username) {
		super.setUsername(username);
	}
	
	@Override
	public String getPassword() {
		return super.getPassword();
	}
	
	@Override
	public void setPassword(String password) {
		super.setPassword(password);
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
