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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "tbl_account")
public class account_m {
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name = "acc_no",nullable = false)
	private int acc_no;
	
	@Column(name = "user_id",nullable = false)
	@NotEmpty(message = "The user id can't be Empty")
	@NotBlank 
	private String user_id;
	
//	@Column(name = "customer_id",nullable = false)
//	private int customer_id;
	
	@Column(name = "log_pass",nullable = false)
	@NotEmpty(message = "The login password can't be Empty")
	private String log_pass;
	
	@Column(name = "trans_pass",nullable = false)
	@NotEmpty(message = "The transaction password can't be Empty")
	private String trans_pass;
	
	@Column(name = "balance",nullable = false)
	private int balance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private customer_m customer;
	public int getBalance() {
		return balance;
	}
	public void setCustomer(customer_m customer) {
		this.customer = customer;
	}
	public customer_m getCustomer() {
		return this.customer;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(int acc_no) {
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
