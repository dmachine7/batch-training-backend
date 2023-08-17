package com.bankapp.app.entity;

import com.bankapp.app.entity.AuthResponse.AuthResponseBuilder;

//@Data

public class AuthResponse {

    private String jwtToken;
    private String username;
	public static AuthResponseBuilder builder() {
		return new AuthResponseBuilder();
	}
	
	public static class AuthResponseBuilder {
		private AuthResponse authResponse = new AuthResponse();
		
		public AuthResponseBuilder jwtToken(String jwtToken) {
			authResponse.setJwtToken(jwtToken);
			return this;
		}
		
		public AuthResponseBuilder username(String username) {
			authResponse.setUserName(username);
			return this;
		}
		
		public AuthResponse build() {
			return authResponse;
		}
	}
	public String getJwtToken() {
		return jwtToken;
	}
	
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
    
    
    
}

