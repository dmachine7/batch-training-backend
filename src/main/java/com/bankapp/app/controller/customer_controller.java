package com.bankapp.app.controller;

import java.util.List;

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

import com.bankapp.app.exception.ResourceNotFoundException;
import com.bankapp.app.model.customer_m;
import com.bankapp.app.service.customer_implementation;

@RestController
@RequestMapping("/api/customer")
public class customer_controller {
	@Autowired
	private customer_implementation customer_service_provider;
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	@GetMapping("/getAll")
	public List<customer_m> getAllLogin() {
		return customer_service_provider.getAllLogin();
	}
	@GetMapping("/{id}")
	public ResponseEntity<customer_m> getById(@PathVariable Integer id ){
		return  ResponseEntity.ok(customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id)));
	}
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public String getData(@Validated @RequestBody customer_m log_user){
		customer_service_provider.saveLogin(log_user);
		return "Added Successfully";		
	}
	//post mappings end
	//update/put mappings start
	@PutMapping("/update/{id}")
	public ResponseEntity<customer_m> update_user(@PathVariable int id, @RequestBody customer_m user_details){
		customer_m find_user = customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id));
		find_user.setName(user_details.getName());
		find_user.setRes_address(user_details.getRes_address());
		customer_m updated_user = customer_service_provider.saveLogin(find_user);
		return ResponseEntity.ok(updated_user);
	}
	//update/put mappings end
	//delete mappings start
	@DeleteMapping("/remove/{id}")
	public String delete_user(@PathVariable int id){
		customer_m find_user = customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id));
		customer_service_provider.remove_user(find_user);
		return "user deleted";
	}
	//delete mappings end
	
}






