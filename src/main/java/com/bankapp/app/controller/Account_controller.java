package com.bankapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.exception.ResourceNotFoundException;
import com.bankapp.app.model.Account;
import com.bankapp.app.service.Account_implementation;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("http://localhost:3000/")
public class Account_controller {
	@Autowired
	private Account_implementation account_service_provider;
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	@GetMapping("/getAll")
	public List<Account> getAllLogin() {
		return account_service_provider.getAllLogin();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Account> getById(@PathVariable Integer id ){
		return ResponseEntity.ok(account_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("account not found for this id :: " + id)));
	}
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public ResponseEntity<Account> getData(@Validated @RequestBody Account log_user){
		account_service_provider.saveLogin(log_user);
		return ResponseEntity.ok(log_user);		
	}
	//post mappings end
	//update/put mappings start
		@PutMapping("/update/{id}")
		public ResponseEntity<Account> update_user(@PathVariable int id, @RequestBody Account user_details){
			Account find_user = account_service_provider.getById(id).orElseThrow(
					()-> new ResourceNotFoundException("account not found for this id :: " + id)
					);
			find_user.setUser_id(user_details.getUser_id());
			find_user.setLog_pass(user_details.getLog_pass());
			Account updated_user = account_service_provider.saveLogin(find_user);
			return ResponseEntity.ok(updated_user);
		}
		//update/put mappings end
		//delete mappings start
		@DeleteMapping("/remove/{id}")
		public String delete_user(@PathVariable int id){
			Account find_user = account_service_provider.getById(id).orElseThrow(
					()-> new ResourceNotFoundException("account not found for this id :: " + id)
					);
			account_service_provider.remove_user(find_user);
			return "user deleted";
		}
		//delete mappings end
}





