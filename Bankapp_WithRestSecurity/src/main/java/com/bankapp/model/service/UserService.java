package com.bankapp.model.service;
import java.util.*;
import com.bankapp.model.entities.AppUser;

public interface UserService {
	public AppUser findByEmail(String email);
	public void addUser(AppUser user);
	public AppUser findByName(String name);
	
	public List<AppUser>findAllUser();
	
	
	public List<AppUser> findAll();
	public void blockUser(Long userId);
	public void deleteUser(Long userId);
}
