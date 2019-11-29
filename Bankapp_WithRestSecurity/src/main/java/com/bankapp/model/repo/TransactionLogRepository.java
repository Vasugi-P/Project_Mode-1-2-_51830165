package com.bankapp.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.model.entities.TransactionLog;
import com.bankapp.model.entities.AccountTransaction;
import com.bankapp.model.entities.AppUser;
@Repository
public interface TransactionLogRepository extends  JpaRepository<TransactionLog, Long> {
	//find all tx between two given date
	//find all tx which contain account id 121
	Optional<TransactionLog> findById(Long id);
	
}
