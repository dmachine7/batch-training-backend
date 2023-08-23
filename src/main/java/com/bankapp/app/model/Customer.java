package com.bankapp.app.model;

import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_customer")
public class Customer {
	@Id
	@Column(name = "email",nullable = false)
	@Email(message = "This is not a valid Email.")
	private String email;

	@Column(name = "acc_no", nullable = false)
	private String acc_no ;
	
	@Column(name = "title",nullable = false)
	@NotEmpty(message = "Title field can't be empty.")
	private String title;
	
	@Column(name = "name",nullable = false)
	@NotEmpty(message = "name field can't be empty.")
	private String name;
	
	@Column(name = "father_name",nullable = false)
	@NotEmpty(message = "Father name field can't be empty.")
	private String father_name;
	
	@Column(name = "mobile",nullable = false)
	@NotEmpty(message = "Mobile number field can't be empty.")
	@Size(min = 10, max = 10, message = "Mobile number must be of 10 characters.")
	private String mobile;
	
	@Column(name = "aadhar",nullable = false)
	@NotEmpty(message = "Aadhar Number field can't be empty.")
	@Size(min = 12, max = 12, message = "Aadhar Number must be of 12 characters.")
	private String aadhar;
	
	@Column(name = "dob",nullable = false)
	private String dob;
	
	@Column(name = "per_address",nullable = false)
	@NotEmpty(message = "Address field can't be empty.")
	private String per_address;
	
	@Column(name = "res_address",nullable = false)
	private String res_address;
	
	@Column(name = "occ_type",nullable = false)
	private String occ_type;
	
	@Column(name = "gross_annual_income",nullable = false)
	@NotEmpty(message = "gross_annual_income field can't be empty.")
	private String gross_annual_income;
	

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "email")
	private Set<Account>account_mm;
	
	
	public Set<Account> getAccount_m() {
		return account_mm;
	}
	public void setAccount_m(Set<Account> account_mm) {
		this.account_mm= account_mm;
	}
	public Customer() {}
	public Customer
	(String email, 
						String acc_no,
						String title,
						String name,
						String father_name,
						String mobile,
						String aadhar,
						String dob, 
						String per_address, 
						String res_address,
						String occ_type,
						String gross_annual_income
						) {

		
		this.acc_no = acc_no;
		this.title = title;
		this.name = name;
		this.father_name = father_name;
		this.mobile = mobile;
		this.email = email;
		this.aadhar = aadhar;
		this.dob = dob;
		this.per_address = per_address;
		this.res_address = res_address;
		this.occ_type = occ_type;
		this.gross_annual_income = gross_annual_income;
		
	}

	

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
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










