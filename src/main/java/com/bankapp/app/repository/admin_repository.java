package com.bankapp.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.admin_m;
import com.bankapp.app.model.customer_m;

@Repository
public interface admin_repository extends JpaRepository<admin_m,Integer>{
		@Query(value = "select * from tbl_admin t where t.log_user = ?1 and t.log_pass = ?2", nativeQuery = true)
		public Optional<admin_m> check_login(String username, String password);
}
