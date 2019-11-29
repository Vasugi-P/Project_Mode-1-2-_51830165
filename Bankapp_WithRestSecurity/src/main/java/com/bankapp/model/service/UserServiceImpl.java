package com.bankapp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.model.entities.AppUser;
import com.bankapp.model.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public AppUser findByEmail(String email) {		
		return repo.findByEmail(email);
	}

	@Override
	public void addUser(AppUser user) {
	repo.save(user);
		
	}

	@Override
	public List<AppUser> findAll() {
		return repo.findAll();
	}

	@Override
	public void blockUser(Long userId) {
		
		
	}

	@Override
	public void deleteUser(Long userId) {
	
		
	}

	@Override
	public AppUser findByName(String name) {
				return repo.findByName(name);
	}

	
	
	@Override
	public List<AppUser> findAllUser() {
		
		return repo.findAll();
	}

}
