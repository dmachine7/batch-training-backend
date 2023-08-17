package com.bankapp.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.customer_m;

@Repository
public interface customer_repository extends JpaRepository<customer_m,Integer>{
	@Query(value = "select * from tbl_customer t where t.acc_no = ?1 ", nativeQuery = true)
	 public Optional<customer_m> getCustomerAcc(int acc_no);

}
