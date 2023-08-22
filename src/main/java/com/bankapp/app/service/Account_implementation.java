package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.Account;
import com.bankapp.app.repository.Account_repository;

@Service
public class Account_implementation implements Account_service{
	
	@Autowired
	private Account_repository account_repo;

	@Override
	public Account saveLogin(Account login) {
		return account_repo.save(login);
	}

	@Override
	public List<Account> getAllLogin() {
		return account_repo.findAll();
	}

	@Override
	public Optional<Account> getById(int id) {
		return account_repo.findById(id);
	}

	@Override
	public void remove_user(Account account) {
		account_repo.delete(account);
		
	}

}