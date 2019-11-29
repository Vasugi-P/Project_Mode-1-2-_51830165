package com.bankapp.model.service;

import java.util.List;
import java.util.Optional;

import com.bankapp.model.entities.TransactionLog;

public interface TransactionLogService {
	public List<TransactionLog> getAllTransaction();
	
	public Optional<TransactionLog> getTransactionById(Long id);
	
	
	
	
	

}
