package com.bankapp.app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_transaction")
public class transaction_m {
	@Id
	@GeneratedValue(strategy =  GenerationType.SEQUENCE)
	@Column(name = "trans_id",nullable = false)
	private int trans_id;
	@Column(name = "send_acc",nullable = false)
	private String send_acc;
	@Column(name = "rec_acc",nullable = false)
	private String rec_acc;
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
	public String getTrans_pass() {
		return trans_pass;
	}
	public void setTrans_pass(String trans_pass) {
		this.trans_pass = trans_pass;
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
	@Column(name = "trans_type",nullable = false)
	private String trans_type;
	@Column(name = "trans_pass",nullable = false)
	private String trans_pass;
	@DateTimeFormat(pattern = "dd-MM-yyy")
	@Column(name = "date",nullable = false)
	private Date date;
	@Column(name = "amount",nullable = false)
	private long amount;
	@Column(name = "remarks",nullable = false)
	private String remarks;
	@Column(name = "maturity_ins",nullable = false)
	private String maturity_ins;
}
