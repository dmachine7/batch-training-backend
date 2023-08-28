package com.bankapp.app.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.app.exception.ResourceNotFoundException;
import com.bankapp.app.model.Account;
import com.bankapp.app.model.Transaction;
import com.bankapp.app.service.AccountImplementation;
import com.bankapp.app.service.TransactionImplementation;


@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("http://localhost:3000/")
public class TransactionController {
	@Autowired
	private TransactionImplementation transaction_service_provider;
	@Autowired
	private AccountImplementation account_service_provider;
	//get mappings start
	@GetMapping("/testing")
	public String test() {
		return "test successful";
	}
	@GetMapping("/getAll")
	public List<Transaction> getAllLogin() {
		return transaction_service_provider.getAllLogin();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object> > getbyacc(@PathVariable int id ){
		Map<String, Object> response = new HashMap<>();
		List<Transaction> trans_list = null;

		try{
			trans_list = transaction_service_provider.getByAcc(id)
				.orElseThrow(()-> new ResourceNotFoundException("transaction not found for this id :: " + id));
		}
		catch(ResourceNotFoundException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", trans_list);
			return ResponseEntity.ok(response);
		}
		catch(Exception e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", trans_list);
			return ResponseEntity.ok(response);
		}
		
		if(trans_list.isEmpty()) {
			response.put("success", false);
			response.put("message","No transactions yet");
			response.put("transaction", trans_list);
			return ResponseEntity.ok(response);
		}
		response.put("success", true);
		response.put("message","data present");
		response.put("transaction", trans_list);	
		return ResponseEntity.ok(response);

			
	}
	
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public ResponseEntity<Map<String,Object>> getData(@Validated @RequestBody Transaction transaction
			){//,@RequestParam String password){
		Map<String, Object> response = new HashMap<>();
		Account checksend = new Account();
		Account checkrecieve = new Account();
		try{
			checksend = account_service_provider.getById(transaction.getSend_acc()).orElseThrow(
			()-> new ResourceNotFoundException("account not found for this id :: " + transaction.getSend_acc()));
		}
		catch(ResourceNotFoundException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", transaction);
			return ResponseEntity.ok(response);
		}
		try {
			checkrecieve = account_service_provider.getById(transaction.getRec_acc()).orElseThrow(
				()-> new ResourceNotFoundException("account not found for this id :: " + transaction.getRec_acc()));
		}
		catch(ResourceNotFoundException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", transaction);
			return ResponseEntity.ok(response);
		}
		transaction.setDate(new Date());
		if(checkrecieve.getAccount_status()==1 ) {//&& checksend.getTrans_pass()==password) {
					if(checksend.getBalance()-transaction.getAmount() < 0) {
						response.put("success", false);
						response.put("message", "insufficient balance!");
						response.put("transaction", transaction);
						return ResponseEntity.ok(response);
					}
					System.out.println(checksend.getBalance()-transaction.getAmount());
					checksend.setBalance(checksend.getBalance()-transaction.getAmount());
					account_service_provider.saveLogin(checksend);
					checkrecieve.setBalance(checkrecieve.getBalance()+transaction.getAmount());
					account_service_provider.saveLogin(checkrecieve);	
					transaction_service_provider.saveLogin(transaction);
					response.put("success", true);
					response.put("message", "transaction successful");
					response.put("transaction", transaction);
					return ResponseEntity.ok(response);
				}
		response.put("success", false);
		response.put("message", "invalid credentials");
		response.put("transaction", transaction);
		return ResponseEntity.ok(response);
	}
	@PostMapping("/sendDataSelf")
	public ResponseEntity<Map<String,Object>> sendDataSelf(@Validated @RequestBody Transaction transaction
			){//,@RequestParam String password){
		Map<String, Object> response = new HashMap<>();
		Account checksend = new Account();
		Account checkrecieve = new Account();
		try{
			checksend = account_service_provider.getById(transaction.getSend_acc()).orElseThrow(
			()-> new ResourceNotFoundException("account not found for this id :: " + transaction.getSend_acc()));
		}
		catch(ResourceNotFoundException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", transaction);
			return ResponseEntity.ok(response);
		}
		try {
			checkrecieve = account_service_provider.getById(transaction.getRec_acc()).orElseThrow(
				()-> new ResourceNotFoundException("account not found for this id :: " + transaction.getRec_acc()));
		}
		catch(ResourceNotFoundException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			response.put("transaction", transaction);
			return ResponseEntity.ok(response);
		}
		transaction.setDate(new Date());
		if(transaction.getPayment_type().compareTo("debit")==0) {//&& checksend.getTrans_pass().equals(password)) {
					if(checksend.getBalance()-transaction.getAmount() < 0) {
						response.put("success", false);
						response.put("message","insufficient balance!");
						response.put("transaction", transaction);
						return ResponseEntity.ok(response);
				}
					System.out.println(checksend.getBalance()-transaction.getAmount());
					checksend.setBalance(checksend.getBalance()-transaction.getAmount());
					account_service_provider.saveLogin(checksend);
					transaction_service_provider.saveLogin(transaction);
					response.put("success", true);
					response.put("message","transaction successful");
					response.put("transaction", transaction);
					return ResponseEntity.ok(response);
		}
		else if(transaction.getPayment_type().compareTo("credit")==0 ){//&&  checksend.getTrans_pass().equals(password)) {
			System.out.println(checksend.getBalance()+transaction.getAmount());
			checksend.setBalance(checksend.getBalance()+transaction.getAmount());
			account_service_provider.saveLogin(checksend);
			transaction_service_provider.saveLogin(transaction);
			response.put("success", false);
			response.put("message", "transaction successful");
			response.put("transaction", transaction);
			return ResponseEntity.ok(response);
		}
		response.put("success", false);
		response.put("message","invalid credentials");
		response.put("transaction", transaction);
		return ResponseEntity.ok(response);
		
    }
	
	//post mappings end
	//update/put mappings start
	@PutMapping("/update/{id}")
	public ResponseEntity<Transaction> update_user(@PathVariable int id, @RequestBody Transaction transaction){
		Transaction find_transaction = transaction_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("transaction not found for this id :: " + id)
				);
		find_transaction.setTrans_id(transaction.getTrans_id());
		find_transaction.setAmount(transaction.getAmount());
		Transaction updated_transaction = transaction_service_provider.saveLogin(find_transaction);
		return ResponseEntity.ok(updated_transaction);
	}
	//update/put mappings end
	//delete mappings start
	@DeleteMapping("/remove/{id}")
	public String delete_user(@PathVariable int id){
		Transaction find_transaction = transaction_service_provider.getById(id).orElseThrow(
				()-> new ResourceNotFoundException("transaction not found for this id :: " + id)
				);
		transaction_service_provider.remove_user(find_transaction);
		return "transaction deleted";
	}
	//delete mappings end
}
