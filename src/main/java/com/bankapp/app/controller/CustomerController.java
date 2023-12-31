package com.bankapp.app.controller;

import java.util.*;

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
import com.bankapp.app.model.Customer;
import com.bankapp.app.service.AccountImplementation;
import com.bankapp.app.service.CustomerImplementation;




@RestController
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:3000/")
public class CustomerController {
	
	public class custom_response {
		private Customer customer_data;
		private String response;
		public Customer getCustomer_data() {
			return customer_data;
		}
		public void setCustomer_data(Customer customer_data) {
			this.customer_data = customer_data;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
		
		
	}
	@Autowired
	private CustomerImplementation customer_service_provider;
	
	@Autowired
	private AccountImplementation account_service_provider;
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	@GetMapping("/getAll")
	public List<Customer> getAllLogin() {
		return customer_service_provider.getAllLogin();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getById(@PathVariable String id ){
		return  ResponseEntity.ok(customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id)));
	}
	@GetMapping("/getByAcc/{id}")
	public ResponseEntity<Customer > getCustomerAcc(@PathVariable String id ){
		
			Customer customer_acc = customer_service_provider.getCustomerAcc(id).orElseThrow(
					()-> new ResourceNotFoundException("transaction not found for this id :: " + id)
					);
			return ResponseEntity.ok(customer_acc);
		
		
	}
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public ResponseEntity<Customer > getData(@Validated @RequestBody Customer log_user){
		Customer temp = customer_service_provider.saveLogin(log_user);
		System.out.println(temp.getAcc_no());
		return ResponseEntity.ok(temp);	
	}
  
	//post mappings end
	//update/put mappings start
	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> update_user(@PathVariable String id, @RequestBody Customer user_details){
		Customer find_user = customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id));
		find_user.setAcc_no(user_details.getAcc_no());
		find_user.setTitle(user_details.getTitle());
		find_user.setName(user_details.getName());
		find_user.setFather_name(user_details.getFather_name());
		find_user.setEmail(user_details.getEmail());
		find_user.setMobile(user_details.getMobile());
		find_user.setAadhar(user_details.getAadhar());
		find_user.setDob(user_details.getDob());
		find_user.setPer_address(user_details.getPer_address());
		find_user.setRes_address(user_details.getRes_address());
		find_user.setOcc_type(user_details.getOcc_type());
		find_user.setGross_annual_income(user_details.getGross_annual_income());
		find_user.setAccountStatus(user_details.getAccountStatus());
		
		Customer updated_user = customer_service_provider.saveLogin(find_user);
		Account updated_account = account_service_provider.getAccountByEmailId(find_user.getEmail()).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id));
		updated_account.setAccount_status(updated_user.getAccountStatus());
	    Account updated_user_account = account_service_provider.saveLogin(updated_account);
		return ResponseEntity.ok(updated_user);
	}
	//update/put mappings end
	//delete mappings start
	@DeleteMapping("/remove/{id}")
	public String delete_user(@PathVariable String id){
		Customer find_user = customer_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("Product not found for this id :: " + id));
		customer_service_provider.remove_user(find_user);
		return "user deleted";
	}
	//delete mappings end
	
}






