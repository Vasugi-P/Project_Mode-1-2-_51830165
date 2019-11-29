package com.bankapp.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.entities.Account;
import com.bankapp.model.entities.Customer;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.exceptions.CustomerNotFoundException;
import com.bankapp.web.controller.formbean.AccountForm;
import com.bankapp.web.controller.formbean.FormBean;
import com.bankapp.web.controller.formbean.MoneyForm;


@RestController
@RequestMapping(path = "/hello/account")
public class AccountRestController {

	private AccountService as;

	@Autowired
	public AccountRestController(AccountService as) {
		super();
		this.as = as;
	}

	@GetMapping(path = "lol")
	private String hello() {
		return "hellooooooooooo";
	}

	@GetMapping(path = "customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAllCustomer() {
		return as.getAllCustomer();
	}

	@GetMapping(path = "account", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccount() {
		return as.getAllAccounts();
	}
	

	@GetMapping(path = "customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getAlCustomer(@PathVariable(name = "id") int id) {
		Customer customer = as.getCustomerById(id).orElseThrow(CustomerNotFoundException::new);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	
	  @DeleteMapping(path="customer/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Void> removeCustomer(@PathVariable(name="id") Long id){
	  as.removeCustomer(id);
	  return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	  }
	  
	
	  @PutMapping(path="account/{accountNumber}") public
	  ResponseEntity<Account>updateAcc(@RequestBody FormBean formBean,
			  @PathVariable(name="accountNumber") Long accountNumber
	   ){ 
		  Customer cust=as.getCustomerById(accountNumber);
		  cust.setName(formBean.getName());
		  cust.setEmail(formBean.getEmail());
		  cust.setAddress(formBean.getAddress());
		  cust.setCity(formBean.getCity());
		  cust.setCountry(formBean.getCountry());
		  Account acc=as.getAccountById(accountNumber);
		  acc.setBalance(formBean.getBalance());
		  acc.setBlocked(formBean.isBlocked());
		  acc.setCustomer(cust);		  
	  cust.setAccount(acc); 
	  return new ResponseEntity<Account>(as.updateAccount(acc),HttpStatus.OK);
	  }
	 
	  @PostMapping(path="customer",consumes =MediaType.APPLICATION_JSON_VALUE,
			  produces=MediaType.APPLICATION_JSON_VALUE) 
	  public ResponseEntity<Account>addAccc( @RequestBody FormBean formBean){ 
		  Customer cust=new Customer(formBean.getName(), formBean.getEmail(),formBean.getPhone()
				  ,formBean.getAddress(),formBean.getCity(),formBean.getCountry());
		  Account acc=new Account(formBean.getBalance(),cust,formBean.isBlocked());
		  cust.setAccount(acc);
		  return new ResponseEntity<Account>(as.addAccount(acc),HttpStatus.OK); 
		  }
	  
	  
}
