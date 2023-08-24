package com.bankapp.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	
	@Query(value = "select * from tbl_transaction t where t.send_acc = ?1 or t.rec_acc = ?1", nativeQuery = true)
	 public Optional<List<Transaction>> getAccountTrans(int acc_no);

}
