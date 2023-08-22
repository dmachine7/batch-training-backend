package com.bankapp.app.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.Admin;
import com.bankapp.app.repository.Admin_repository;

@Service
public class Admin_implementation implements Admin_service{
	
	@Autowired
	private Admin_repository admin_repo;

	@Override
	public Optional<Admin> check_login(String username, String password) {
		return admin_repo.check_login(username, password);
		
	}
	
	


}
