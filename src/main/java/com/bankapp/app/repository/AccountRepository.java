package com.bankapp.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,String>{
	public Optional<Account> findByEmail(String email);
}
