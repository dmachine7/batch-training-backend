package com.bankapp.app.entity;


public class AuthResponse {

    private String jwtToken;
    private String username;
    private String accNo;
    private long balance;
    private int accStatus;
<<<<<<< HEAD
    private String password;
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
=======
    private int isAdmin;
    
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
>>>>>>> 3df46cf647166766e111dba0e0fea6b75adf250f
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
		this.accStatus = accStatus;
	}

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
		public AuthResponseBuilder password(String password) {
			authResponse.setPassword(password);
			return this;
		}
		
		public AuthResponseBuilder accNo(String accNo) {
			authResponse.setAccNo(accNo);
			return this;
		}
		
		public AuthResponseBuilder balance(long balance) {
			authResponse.setBalance(balance);
			return this;
		}
		
		public AuthResponseBuilder accStatus(int accStatus) {
			authResponse.setAccStatus(accStatus);
			return this;
		}
		
		public AuthResponseBuilder isAdmin(int isAdmin) {
			authResponse.setIsAdmin(isAdmin);
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


