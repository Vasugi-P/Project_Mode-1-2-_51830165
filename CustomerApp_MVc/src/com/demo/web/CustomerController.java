package com.demo.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.persistance.Customer;
import com.demo.model.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerservice;

	

	@RequestMapping(value = "allcustomers", method = RequestMethod.GET)
	public String getCustomer(ModelMap map) {
		map.addAttribute("customers", customerservice.getAllCustomers());
		return "allcustomers";
		
		//it goes to allbooks.jsp under view resolver
	}

	@RequestMapping(value = "addcustomer", method = RequestMethod.GET)
	public String addCustomerGet(ModelMap map) {
		map.addAttribute("customer", new Customer());
		return "addcustomer";
		
		//when we click the href it comes to this code called addbook
	}

	@RequestMapping(value = "addcustomer", method = RequestMethod.POST)
	public String addCustomerUpdatePost( @Valid  Customer customer,BindingResult bindingresult) {
		if(bindingresult.hasErrors()){
			return "addcustomer";
		}else{
		if (customer.getId() == 0) {
			customerservice.addCustomer(customer);
		} else {
			customerservice.updateCustomer(customer);
		}

		return "redirect:allcustomers";
	}
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteCustomer(HttpServletRequest request) {
		int customerId = Integer.parseInt(request.getParameter("id").trim());
		customerservice.removeCustomer(customerId);
		return "redirect:allcustomers";
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateCustomerGet(HttpServletRequest request, ModelMap map) {
		int customerId = Integer.parseInt(request.getParameter("id").trim());
		Customer customerToBeUpdate = customerservice.getCustomerById(customerId);
		map.addAttribute("customer", customerToBeUpdate);
		return "addcustomer";
	}
	//this method will run before req
	//return method willl be put
	
	

}
