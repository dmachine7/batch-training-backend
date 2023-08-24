package com.bankapp.app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "trans_id",nullable = false)
	private int trans_id;
	
	@Column(name = "send_acc",nullable = false)
	@NotEmpty(message = "The sender's account number can't be Empty")
	private String send_acc;
	
	@Column(name = "rec_acc",nullable = false)
	@NotEmpty(message = "The sender's account number can't be Empty")
	private String rec_acc;
	
	@Column(name = "trans_type")
	private String trans_type;
	
	@Column(name = "payment_type")
	private String payment_type;
	
	@DateTimeFormat(pattern = "dd-MM-yyy")
	@Column(name = "date",nullable = false)
	private Date date;
	
	@Column(name = "amount",nullable = false)
	private long amount;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "maturity_ins")
	private String maturity_ins;
	
	public Transaction() {
        // Default constructor
    }
	
	public Transaction(int trans_id, String send_acc, String rec_acc, String trans_type,
			String payment_type, Date date, long amount, String remarks, String maturity_ins) {
		super();
		this.trans_id = trans_id;
		this.send_acc = send_acc;
		this.rec_acc = rec_acc;
		this.trans_type = trans_type;
		this.payment_type = payment_type;
		this.date = date;
		this.amount = amount;
		this.remarks = remarks;
		this.maturity_ins = maturity_ins;
	}
	public int getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}
	public String getSend_acc() {
		return send_acc;
	}
	public void setSend_acc(String send_acc) {
		this.send_acc = send_acc;
	}
	public String getRec_acc() {
		return rec_acc;
	}
	public void setRec_acc(String rec_acc) {
		this.rec_acc = rec_acc;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getMaturity_ins() {
		return maturity_ins;
	}
	public void setMaturity_ins(String maturity_ins) {
		this.maturity_ins = maturity_ins;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
}
