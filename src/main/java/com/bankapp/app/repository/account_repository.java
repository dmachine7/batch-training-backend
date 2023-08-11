package com.bankapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.account_m;

@Repository
public interface account_repository extends JpaRepository<account_m,Integer>{

}
