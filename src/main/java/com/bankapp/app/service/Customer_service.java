package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.Customer;

public interface Customer_service {
	public Customer saveLogin(Customer login);
	public List<Customer> getAllLogin();
	public Optional<Customer> getById(int id);
	public void remove_user(Customer customer);
	//public String insertData(login_m user_data);
}