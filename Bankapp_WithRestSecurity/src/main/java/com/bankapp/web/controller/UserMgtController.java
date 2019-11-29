package com.bankapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.model.entities.AppUser;
import com.bankapp.model.service.UserService;


@RestController
@RequestMapping(path="/hello/user")
public class UserMgtController {
	
	@Autowired
	private UserService us;
	
	
	@GetMapping(path="appuser",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AppUser>getAllUser(){
		return us.findAllUser();
	}

}
