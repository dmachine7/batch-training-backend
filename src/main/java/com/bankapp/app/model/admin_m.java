package com.bankapp.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_admin")
public class admin_m {
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name = "id",nullable = false)
	private int id;
	
	@Column(name = "name",nullable = false)
	@NotEmpty(message = "name field can't be empty.")
	private String name;
	
	@Column(name = "log_user",nullable = false)
	@NotEmpty(message = "The login username can't be Empty")
	private String username;
	
	@Column(name = "log_pass",nullable = false)
	@NotEmpty(message = "The login password can't be Empty")
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLog_user() {
		return username;
	}

	public void setLog_user(String log_user) {
		this.username = log_user;
	}

	public String getLog_pass() {
		return password;
	}

	public void setLog_pass(String log_pass) {
		this.password = log_pass;
	}
	
	

}
