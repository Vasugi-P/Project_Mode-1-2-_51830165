package com.demo.model.service;

public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String msg) {
		super(msg);
	}


}
