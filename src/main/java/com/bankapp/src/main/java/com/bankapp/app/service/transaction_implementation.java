package com.bankapp.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.transaction_m;
import com.bankapp.app.repository.transaction_repository;

@Service
public class transaction_implementation implements transaction_service{
	@Autowired
	private transaction_repository transaction_repo;

	@Override
	public transaction_m saveLogin(transaction_m transaction) {
		return transaction_repo.save(transaction);
	}

	@Override
	public List<transaction_m> getAllLogin() {
		return transaction_repo.findAll();
	}

	@Override
	public Optional<transaction_m> getById(int id) {
		return transaction_repo.findById(id);
	}

	@Override
	public void remove_user(transaction_m transaction) {
		transaction_repo.delete(transaction);
		
	}

}
