package com.bankapp.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bankapp.model.entities.Account;
import com.bankapp.model.entities.AppUser;
import com.bankapp.model.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/allusers")
	public ModelAndView getAllAppUsers(ModelAndView mv){
		mv.setViewName("allappusers");
		mv.addObject("users", userService.findAll());
		return mv;
	}
	/*
	 * @GetMapping(path="adduser") public String addUserGet(Model model) {
	 * model.addAttribute("aduser",new AppUser()); return "adduser"; }
	 */
	
	@RequestMapping(value = "adduser", method = RequestMethod.GET)
	public String addUserGet(Model model) {
		model.addAttribute("aduser", new AppUser());
		return "adduser";		
	}
	@RequestMapping(value = "adduser", method = RequestMethod.POST)
	public String addUserPost( @Valid  AppUser appUser,BindingResult bindingresult) {
		if(bindingresult.hasErrors()){
			return "adduser";
		}else{
		if (appUser.getId() == 0) {
			userService.addUser(appUser);
		} else {
			userService.editUser(appUser);
		}

		return "redirect:allappusers";
	}
	}
	

}
