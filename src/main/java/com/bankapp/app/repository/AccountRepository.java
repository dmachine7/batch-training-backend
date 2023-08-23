package com.bankapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

}
