package com.bata.billpunch.login.bean;

public class TokenResponse {

	
	private String status;
	private String username;
	private String userrole;
	private String fullName;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public TokenResponse(String status,String username, String userrole, String fullName) {
		this.status = status;
		this.username = username;
		this.userrole=userrole;
		this.fullName=fullName;
		
	}

	

	
}
