package com.bankapp.app.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.app.model.admin_m;
import com.bankapp.app.repository.admin_repository;

@Service
public class admin_implementation implements admin_service{
	
	@Autowired
	private admin_repository admin_repo;

	@Override
	public Optional<admin_m> check_login(String username, String password) {
		return admin_repo.check_login(username, password);
		
	}
	
	


}
