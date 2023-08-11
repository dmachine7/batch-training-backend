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
	private String trans_id;
	@Column(name = "send_acc",nullable = false)
	private String send_acc;
	@Column(name = "rec_acc",nullable = false)
	private String rec_acc;
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
