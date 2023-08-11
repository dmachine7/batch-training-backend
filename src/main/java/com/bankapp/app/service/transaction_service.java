package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;
import com.bankapp.app.model.transaction_m;

public interface transaction_service {
	public transaction_m saveLogin(transaction_m transaction);
	public List<transaction_m> getAllLogin();
	public Optional<transaction_m> getById(int id);
	public void remove_user(transaction_m transaction);

}
