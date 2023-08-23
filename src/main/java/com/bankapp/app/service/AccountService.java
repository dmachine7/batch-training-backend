package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.Account;

public interface AccountService {
	public Account saveLogin(Account login);
	public List<Account> getAllLogin();
	public Optional<Account> getById(String id);
	public void remove_user(Account account);

}
