package com.bankapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.customer_m;

@Repository
public interface customer_repository extends JpaRepository<customer_m,Integer>{

}
