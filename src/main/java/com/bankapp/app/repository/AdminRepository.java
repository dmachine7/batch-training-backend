package com.bankapp.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.app.model.Admin;
import com.bankapp.app.model.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
		@Query(value = "select * from tbl_admin t where t.log_user = ?1 and t.log_pass = ?2", nativeQuery = true)
		public Optional<Admin> check_login(String username, String password);
}
