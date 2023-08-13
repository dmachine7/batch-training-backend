package com.bankapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.transaction_m;

@Repository
public interface transaction_repository extends JpaRepository<transaction_m,Integer>{

}
