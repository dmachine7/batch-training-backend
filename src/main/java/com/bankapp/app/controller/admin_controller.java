package com.bankapp.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.controller.customer_controller.custom_response;
import com.bankapp.app.exception.ResourceNotFoundException;
import com.bankapp.app.model.admin_m;
import com.bankapp.app.service.admin_implementation;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth/admin")
@CrossOrigin("http://localhost:3000/")
public class admin_controller {

	@Autowired
	private admin_implementation admin_service_provider;
	
	public static class login_body {
		private String username;
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	//get mappings end
	@PostMapping("/login")
	public ResponseEntity<admin_m> getData(HttpServletRequest log_user) throws IOException{
		ObjectMapper ob_map = new ObjectMapper();
		byte[] bytes = log_user.getInputStream().readAllBytes();
		login_body response = ob_map.readValue(bytes, login_body.class);
		admin_m db_response = admin_service_provider.check_login(response.getUsername(), response.getPassword())
				.orElseThrow( ()->  new ResourceNotFoundException("user not found for these credentials"));
		return ResponseEntity.ok(db_response);		
	}
}
