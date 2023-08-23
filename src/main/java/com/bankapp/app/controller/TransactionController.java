package com.bankapp.app.controller;


import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<List<Transaction> > getbyacc(@PathVariable int id ){
		
			List<Transaction> trans_list = transaction_service_provider.getByAcc(id).orElseThrow(
					()-> new ResourceNotFoundException("transaction not found for this id :: " + id)
					);
			return ResponseEntity.ok(trans_list);
		
		
	}
	
	//get mappings end
	//post mappings start
	@PostMapping("/sendData")
	public ResponseEntity<Transaction> getData(@Validated @RequestBody Transaction transaction){
		//Account check = account_service_provider.getById(transaction.getSend_acc()).orElseThrow(
			//	()-> new ResourceNotFoundException("account not found for this id :: " + id));;
		transaction_service_provider.saveLogin(transaction);
		return ResponseEntity.ok(transaction);	
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