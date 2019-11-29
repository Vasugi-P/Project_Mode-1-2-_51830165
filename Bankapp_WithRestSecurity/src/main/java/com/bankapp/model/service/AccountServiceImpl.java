package com.bankapp.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.entities.Account;
import com.bankapp.model.entities.AccountTransaction;
import com.bankapp.model.entities.Customer;
import com.bankapp.model.entities.TransactionLog;
import com.bankapp.model.repo.AccountRepository;
import com.bankapp.model.repo.AccountTransactionRepository;
import com.bankapp.model.repo.CustomerRepository;
import com.bankapp.model.repo.TransactionLogRepository;
import com.bankapp.model.service.exceptions.AccountNotFoundException;
import com.bankapp.model.service.exceptions.CustomerNotFoundException;
import com.bankapp.model.service.exceptions.NotSufficientFundException;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountTransactionRepository accountTransactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionLogRepository transactionLogRepository;

	@Override
	public void blockAccount(Long accountNumber) {

	}

	@Override
	public void createAccount(Account account) {
		accountRepository.save(account);
		customerRepository.save(account.getCustomer());
	}

	@Override
	public void deposit(Long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElseThrow(AccountNotFoundException::new);
		account.setBalance(account.getBalance() + amount);
		AccountTransaction accountTransaction = new AccountTransaction("deposit", amount);
		account.addAccountTransaction(accountTransaction);
		accountRepository.save(account);
		TransactionLog log = new TransactionLog(accountNumber, null, "deposit", amount, account.getCustomer().getName(),
				"done");
		transactionLogRepository.save(log);
		// return account;
		// accountTransactionRepository.save(accountTransaction);

	}

	@Override
	public void withdraw(Long accountNumber, double amount) {
		Account account = accountRepository.findById(accountNumber).orElseThrow(AccountNotFoundException::new);
		// min bal should be 1000 in any case
		if (account.getBalance() - amount < 1000)
			throw new NotSufficientFundException();
		AccountTransaction accountTransaction = new AccountTransaction("withdraw", amount);
		account.addAccountTransaction(accountTransaction);
		account.setBalance(account.getBalance() - amount);
		TransactionLog log = new TransactionLog(accountNumber, null, "withdraw", amount,
				account.getCustomer().getName(), "done");
		transactionLogRepository.save(log);
		accountRepository.save(account);
	}

	@Override
	public void transfer(Long fromAccNumber, Long toAccNumber, double amount) {

		Account account = accountRepository.findById(fromAccNumber).orElseThrow(AccountNotFoundException::new);

		if (account.getBalance() - amount < 1000)
			throw new NotSufficientFundException();
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);

		Account account1 = accountRepository.findById(toAccNumber).orElseThrow(AccountNotFoundException::new);
		account1.setBalance(account1.getBalance() + amount);
		accountRepository.save(account1);

		AccountTransaction accountTransaction1 = new AccountTransaction("transfer", amount);
		account.addAccountTransaction(accountTransaction1);
		account1.addAccountTransaction(accountTransaction1);

		TransactionLog log = new TransactionLog(fromAccNumber, toAccNumber, "transfer", amount,
				account.getCustomer().getName(), "done");
		transactionLogRepository.save(log);

	}

	/*
	 * @Override public void transfer(Long fromAccNumber, Long toAccNumber, double
	 * amount) { this.withdraw(fromAccNumber, amount); this.deposit(toAccNumber,
	 * amount);
	 * 
	 * }
	 */

	@Override
	public List<Account> getAllAccounts() {
		return (List<Account>) accountRepository.findAll();
	}

	public Account addAccount(Account account) {
		return accountRepository.save(account);

	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);

	}

	@Override
	public Optional<Customer> getCustomerById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public void removeCustomer(Long id) {
		accountRepository.deleteById(id);

	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);

	}

	@Override
	public Account getAccountById(Long id) {

		return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
	}

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
	}

	
}
