package com.bankapp.model.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.model.entities.TransactionLog;
import com.bankapp.model.repo.TransactionLogRepository;


@Service
@Transactional
public class TransactionLogServiceImpl implements TransactionLogService{
	
	@Autowired
	private TransactionLogRepository transactionLogRepository;

	
	@Override
	public List<TransactionLog> getAllTransaction() {		
		return transactionLogRepository.findAll();
	}


	@Override
	public Optional<TransactionLog> getTransactionById(Long id) {
		
		return transactionLogRepository.findById(id);
	}


	


}
