package com.bankapp.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.model.account_m;
import com.bankapp.app.service.account_implementation;

@RestController
@RequestMapping("/api/account")
public class account_controller {
	@Autowired
	private account_implementation account_service_provider;
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	@GetMapping("/getAll")
	public List<account_m> getAllLogin() {
		return account_service_provider.getAllLogin();
	}
	@GetMapping("/{id}")
	public Optional<account_m> getById(@PathVariable Integer id ){
		return account_service_provider.getById(id);
	}
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public String getData(@Validated @RequestBody account_m log_user){
		account_service_provider.saveLogin(log_user);
		return "Added Successfully";		
	}
	//post mappings end
	//update/put mappings start
		@PutMapping("/update/{id}")
		public ResponseEntity<account_m> update_user(@PathVariable int id, @RequestBody account_m user_details){
			account_m find_user = account_service_provider.getById(id).orElseThrow();
			find_user.setUser_id(user_details.getUser_id());
			find_user.setLog_pass(user_details.getLog_pass());
			account_m updated_user = account_service_provider.saveLogin(find_user);
			return ResponseEntity.ok(updated_user);
		}
		//update/put mappings end
		//delete mappings start
		@DeleteMapping("/remove/{id}")
		public String delete_user(@PathVariable int id){
			account_m find_user = account_service_provider.getById(id).orElseThrow();
			account_service_provider.remove_user(find_user);
			return "user deleted";
		}
		//delete mappings end
}





