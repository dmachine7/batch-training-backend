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
	
	@Column(name = "user_id",nullable = false)
	@NotEmpty(message = "The user id can't be Empty")
	@NotBlank 
	private String user_id;
	
	@Column(name = "customer_id")
	private int customer_id;
	
	@Column(name = "log_pass",nullable = false)
	@NotEmpty(message = "The login password can't be Empty")
	private String log_pass;
	
	@Column(name = "trans_pass",nullable = false)
	@NotEmpty(message = "The transaction password can't be Empty")
	private String trans_pass;
	
	@Column(name = "balance",nullable = false)
	@Min(value = 0)
	private long balance;
	
	public Account() {
		
	}
	public Account(String acc_no, String user_id, int customer_id, String log_pass, String trans_pass,  int balance) {
		super();
		this.acc_no = acc_no;
		this.user_id = user_id;
		this.customer_id = customer_id;
		this.log_pass = log_pass;
		this.trans_pass = trans_pass;
		this.balance = balance;
	}

	public long getBalance() {
		return balance;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
