package com.bankapp.app.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.app.model.Admin;

public interface Admin_service {
	public Optional<Admin> check_login(String username, String password);
	//public List<admin_m> getAllLogin();
	//public Optional<admin_m> getById(int id);
	//public void remove_user(admin_m admin);

}
