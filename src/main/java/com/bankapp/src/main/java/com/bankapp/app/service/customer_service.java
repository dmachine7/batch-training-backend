package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.customer_m;

public interface customer_service {
	public customer_m saveLogin(customer_m login);
	public List<customer_m> getAllLogin();
	public Optional<customer_m> getById(int id);
	public void remove_user(customer_m customer);
	//public String insertData(login_m user_data);
}
