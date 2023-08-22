package com.bankapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.Account;

@Repository
public interface Account_repository extends JpaRepository<Account,Integer>{

}
