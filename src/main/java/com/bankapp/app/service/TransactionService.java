package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;
import com.bankapp.app.model.Transaction;

public interface TransactionService {
	public Transaction saveLogin(Transaction transaction);
	public List<Transaction> getAllLogin();
	public Optional<Transaction> getById(int id);
	public void remove_user(Transaction transaction);

}
