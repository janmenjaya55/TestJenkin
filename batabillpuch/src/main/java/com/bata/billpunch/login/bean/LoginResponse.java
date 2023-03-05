package com.bata.billpunch.login.bean;

import java.io.Serializable;

public class LoginResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private String userrole; 
	
	public LoginResponse(String jwttoken, String userrole) {
		this.jwttoken = jwttoken;
		this.userrole = userrole;
		
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	
	
}