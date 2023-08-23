package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.Customer;
import com.bankapp.app.repository.CustomerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

@Service
public class CustomerImplementation implements CustomerService {
	
	@Autowired
	private CustomerRepository customer_repo;
	@Override
	public Customer saveLogin(Customer login) {
		Customer temp =  customer_repo.save(login);
		return temp;
	}
	@Override
	public List<Customer> getAllLogin() {
		
		return customer_repo.findAll();
	}
	@Override
	public Optional<Customer> getById(int id) {
		
		return customer_repo.findById(id);
	}
	@Override
	public void remove_user(Customer to_be_deleted) {
		customer_repo.delete(to_be_deleted);
	}
	public Optional<Customer > getCustomerAcc(String id) {
		return customer_repo.getCustomerAcc(id);
	}

}
