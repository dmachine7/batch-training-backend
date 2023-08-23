package com.bankapp.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.exception.ResourceNotFoundException;
import com.bankapp.app.model.Transaction;
import com.bankapp.app.repository.TransactionRepository;

@Service
public class TransactionImplementation implements TransactionService{
	@Autowired
	private TransactionRepository transaction_repo;

	@Override
	public Transaction saveLogin(Transaction transaction) {
		return transaction_repo.save(transaction);
	}

	@Override
	public List<Transaction> getAllLogin() {
		return transaction_repo.findAll();
	}

	@Override
	public Optional<Transaction> getById(int id) {
		return transaction_repo.findById(id);
	}

	@Override
	public void remove_user(Transaction transaction) {
		transaction_repo.delete(transaction);
		
	}
	public Optional<List<Transaction> > getByAcc(int id) {
		return transaction_repo.getAccountTrans(id);
	}

}
