package com.bankapp.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.entities.AccountTransaction;
import com.bankapp.model.entities.TransactionLog;
import com.bankapp.model.repo.AccountTransactionRepository;
import com.bankapp.model.service.TransactionLogService;
import com.bankapp.model.service.exceptions.TransactionNotFoundException;


@RestController
@RequestMapping(path="foo")
public class AccountTransactionController {

	private TransactionLogService ts;
	
	@Autowired
	public AccountTransactionController(TransactionLogService ts) {
		super();
		this.ts = ts;
	}

	@GetMapping(path = "faa")
	private String hello() {
		return "helloooooooo";
	}
	
	
	@GetMapping(path="transaction",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TransactionLog> getAllTransaction(){
		return ts.getAllTransaction();
			}
	
	@GetMapping(path="transaction/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionLog>getAllTrans(@PathVariable(name="id")Long id){
		TransactionLog translog=ts.getTransactionById(id).orElseThrow(TransactionNotFoundException::new);
		return new ResponseEntity<TransactionLog>(translog,HttpStatus.OK);
		
		
	}
		
		
}
	

