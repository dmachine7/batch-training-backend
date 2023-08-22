package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.customer_m;
import com.bankapp.app.repository.customer_repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

@Service
public class customer_implementation implements customer_service {
	
	@Autowired
	private customer_repository customer_repo;
	@Override
	public customer_m saveLogin(customer_m login) {
		customer_m temp =  customer_repo.save(login);
		return temp;
	}
	@Override
	public List<customer_m> getAllLogin() {
		
		return customer_repo.findAll();
	}
	@Override
	public Optional<customer_m> getById(int id) {
		
		return customer_repo.findById(id);
	}
	@Override
	public void remove_user(customer_m to_be_deleted) {
		customer_repo.delete(to_be_deleted);
	}
	public Optional<customer_m > getCustomerAcc(int id) {
		return customer_repo.getCustomerAcc(id);
	}

}
