package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.account_m;

public interface account_service {
	public account_m saveLogin(account_m login);
	public List<account_m> getAllLogin();
	public Optional<account_m> getById(int id);
	public void remove_user(account_m account);

}
