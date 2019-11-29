package com.demo.web;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.persistance.User;
import com.demo.model.persistance.UserNotFoundException;
import com.demo.model.service.UserService;

@Controller
public class UserMgtController {
	
	@Autowired
	private UserService userService;
	
	/*@PostConstruct
	public void init(){
		userService.addUser(new User("krish","krish@gmail.com","krish38","admin",true));
		userService.addUser(new User("simbu","simbu@gmail.com","simbu79","admin",true));
		userService.addUser(new User("sethu","sethu@gmail.com","sethu57","admin",true));
	}*/
	@RequestMapping(value = "/")
	public String home(ModelMap map) {
		return "redirect:login";
	}
	
	@RequestMapping(value="login")
	public String loginGet(HttpServletRequest req,ModelMap map) {
		map.addAttribute("userbean",new LoginFormBean());
		map.addAttribute("error",req.getParameter("error"));
		return "login";
	}
	@RequestMapping(value="logout")
	public String logoutGet(HttpServletRequest req,ModelMap map) {
		map.addAttribute("userbean",new LoginFormBean());
		map.addAttribute("message","you are successfully logged out!");
		return "login";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginPost(HttpServletRequest req,@Valid @ModelAttribute(value="userbean")LoginFormBean loginbean,BindingResult bindingResult ) {
		User user=null;
		if(bindingResult.hasErrors()){
			return "login";
		}else{
			try{
				 user=userService.getUser(loginbean.getEmail(),loginbean.getPassword());
			}catch(UserNotFoundException e){
				return "redirect:login?error=login failed";
			}
			HttpSession ht=req.getSession();
			ht.setAttribute("user", user);
		}
		return "redirect:allcustomers";
	}
	
	

}
