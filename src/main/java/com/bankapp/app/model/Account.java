package com.bankapp.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "tbl_account")
public class Account {
	@Id
	@Column(name = "acc_no",nullable = false)
	private String acc_no;
	
	@Column(name = "email",nullable = false)
	@NotEmpty(message = "The username can't be Empty")
	@NotBlank 
	private String email;
	
	@Column(name = "log_pass",nullable = false)
	@NotEmpty(message = "The login password can't be Empty")
	private String log_pass;
	
	@Column(name = "trans_pass",nullable = false)
	@NotEmpty(message = "The transaction password can't be Empty")
	private String trans_pass;
	
	@Column(name = "balance",nullable = false)
	@Min(value = 0)
	private long balance;
	

	@Column(name = "account_status")
	private int account_status;
	
	
	public Account() {
		
	}
	public Account(String acc_no, String email, 
			String customer_email, String log_pass, String trans_pass,
			long balance, int account_status) {
		super();
		this.acc_no = acc_no;
		this.email = email;
		this.log_pass = log_pass;
		this.trans_pass = trans_pass;
		this.balance = balance;
		this.account_status = account_status;
	}

	public int getAccount_status() {
		return account_status;
	}
	public void setAccount_status(int account_status) {
		this.account_status = account_status;
	}
	
	public long getBalance() {
		return balance;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	
	
	public String getLog_pass() {
		return log_pass;
	}
	public void setLog_pass(String log_pass) {
		this.log_pass = log_pass;
	}
	public String getTrans_pass() {
		return trans_pass;
	}
	public void setTrans_pass(String trans_pass) {
		this.trans_pass = trans_pass;
	}
	

}
