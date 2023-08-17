package com.bankapp.app.model;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tbl_customer")
public class customer_m {
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "title",nullable = false)
	@NotEmpty(message = "Title field can't be empty.")
	private String title;
	@Column(name = "name",nullable = false)
	@NotEmpty(message = "Username field can't be empty.")
	@Size(min = 2, max = 30, message = "Username must be between 2 to 30 characters.")
	private String name;
	@Column(name = "father_name",nullable = false)
	@NotEmpty(message = "Father name field can't be empty.")
	@Size(min = 2, max = 30, message = "Father name must be between 2 to 30 characters.")
	private String father_name;
	@Column(name = "mobile",nullable = false)
	@NotEmpty(message = "Mobile number field can't be empty.")
	@Size(min = 2, max = 30, message = "Mobile number must be between 5 to 15 characters.")
	private String mobile;
	@Column(name = "email",nullable = false)
	@Email(message = "This is not a valid Email.")
	private String email;
	@Column(name = "aadhar",nullable = false)
	@NotEmpty(message = "Aadhar Number field can't be empty.")
	@Size(min = 12, max = 12, message = "Aadhar Number must be of 12 characters.")
	private String aadhar;
	@DateTimeFormat(pattern = "dd-MM-yyy")
	@Column(name = "dob",nullable = false)
	private Date dob;
	@Column(name = "per_address",nullable = false)
	@NotEmpty(message = "Address field can't be empty.")
	private String per_address;
	@Column(name = "res_address",nullable = false)
	private String res_address;
	@Column(name = "occ_type",nullable = false)
	private String occ_type;
	@Column(name = "source_income",nullable = false)
	private String source_income;
	@Column(name = "gross_annual_income",nullable = false)
	private String gross_annual_income;
	
	public customer_m() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getRes_address() {
		return res_address;
	}
	public void setRes_address(String res_address) {
		this.res_address = res_address;
	}
	public String getOcc_type() {
		return occ_type;
	}
	public void setOcc_type(String occ_type) {
		this.occ_type = occ_type;
	}
	public String getSource_income() {
		return source_income;
	}
	public void setSource_income(String source_income) {
		this.source_income = source_income;
	}
	public String getGross_annual_income() {
		return gross_annual_income;
	}
	public void setGross_annual_income(String gross_annual_income) {
		this.gross_annual_income = gross_annual_income;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPer_address() {
		return per_address;
	}
	public void setPer_address(String per_address) {
		this.per_address = per_address;
	}
}










