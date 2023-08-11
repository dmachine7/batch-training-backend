package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.account_m;
import com.bankapp.app.repository.account_repository;

@Service
public class account_implementation implements account_service{
	
	@Autowired
	private account_repository account_repo;

	@Override
	public account_m saveLogin(account_m login) {
		return account_repo.save(login);
	}

	@Override
	public List<account_m> getAllLogin() {
		return account_repo.findAll();
	}

	@Override
	public Optional<account_m> getById(int id) {
		// TODO Auto-generated method stub
		return account_repo.findById(id);
	}

	@Override
	public void remove_user(account_m account) {
		account_repo.delete(account);
		
	}

}
