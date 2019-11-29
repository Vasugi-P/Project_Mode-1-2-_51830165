package com.bankapp.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.bankapp.model.entities.Account;
import com.bankapp.model.entities.AccountTransaction;
import com.bankapp.model.entities.Customer;
import com.bankapp.model.entities.TransactionLog;



public interface AccountService {
	public List<Account> getAllAccounts();
	public List<Customer> getAllCustomer();
	
	public Account addAccount(Account account);
	public Account updateAccount(Account account);
	
	public Customer addCustomer(Customer customer);
	public void removeCustomer(Long id);
	 Account getAccountById(Long id );
	 Customer getCustomerById(Long id);
	
	void blockAccount(Long accountNumber);
    void createAccount(Account account );
    void deposit(Long accountNumber, double amount);
    void withdraw(Long accountNumber, double amount);
    void transfer(Long fromAccNumber, Long toAccNumber, double amount);
    public Optional<Customer> getCustomerById(int id);
	
	
}
