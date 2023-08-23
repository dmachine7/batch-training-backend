package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.Customer;

public interface CustomerService {
	public Customer saveLogin(Customer login);
	public List<Customer> getAllLogin();
	public Optional<Customer> getById(String id);
	public void remove_user(Customer customer);
	//public String insertData(login_m user_data);
}
