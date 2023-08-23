package com.bankapp.app.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.Admin;
import com.bankapp.app.repository.AdminRepository;

@Service
public class AdminImplementation implements AdminService{
	
	@Autowired
	private AdminRepository admin_repo;

	@Override
	public Optional<Admin> check_login(String username, String password) {
		return admin_repo.check_login(username, password);
		
	}
	
	


}
