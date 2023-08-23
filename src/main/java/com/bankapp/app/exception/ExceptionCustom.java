package com.bankapp.app.exception;

import java.util.Date;

public class ExceptionCustom {
	    private int statuscode;
	    private Date datetime;
	    private String title;
	    private String description;
	    
	    public ExceptionCustom(int statuscode,Date datetime, String title, String description) {
	    	this.statuscode = statuscode;
	    	this.datetime = datetime;
	    	this.title = title;
	    	this.description = description;
	    }

		public Date getDatetime() {
			return datetime;
		}
		public int getStatuscode() {
			return statuscode;
		}
		public String getTitle() {
			return title;
		}
		public String getDescription() {
			return description;
		}

}
