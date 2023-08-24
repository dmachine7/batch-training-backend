package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.Account;
import com.bankapp.app.repository.AccountRepository;

@Service
public class AccountImplementation implements AccountService{
	
	@Autowired
	private AccountRepository account_repo;

	@Override
	public Account saveLogin(Account login) {
		return account_repo.save(login);
	}

	@Override
	public List<Account> getAllLogin() {
		return account_repo.findAll();
	}

	@Override
	public Optional<Account> getById(String id) {
		return account_repo.findById(id);
	}

	@Override
	public void remove_user(Account account) {
		account_repo.delete(account);
		
	}
	
	@Override
    public  Optional<Account> getAccountByEmailId(String emailId) {
        return account_repo.findByEmail(emailId);
    }

}
