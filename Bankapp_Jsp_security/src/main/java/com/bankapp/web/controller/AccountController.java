package com.bankapp.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bankapp.model.entities.Account;
import com.bankapp.model.entities.Customer;
import com.bankapp.model.repo.AccountRepository;
import com.bankapp.model.repo.CustomerRepository;
import com.bankapp.model.service.AccountService;
import com.bankapp.model.service.AccountServiceImpl;
import com.bankapp.model.service.exceptions.CustomerNotFoundException;
import com.bankapp.web.controller.formbean.AccountForm;
import com.bankapp.web.controller.formbean.FormBean;
import com.bankapp.web.controller.formbean.MoneyForm;

@Controller
@RequestMapping("mgr")
public class AccountController {

	private AccountServiceImpl as;

	@Autowired
	private AccountRepository repo;
	@Autowired
	private CustomerRepository cusrepo;
	@Autowired
	private AccountService accservice;

	@Autowired
	public AccountController(AccountServiceImpl as) {
		super();
		this.as = as;
	}

	@GetMapping(path = "api")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("home");
		mv.addObject("info", "welcome");
		return mv;
	}

	@GetMapping(path = "allAccount")
	public ModelAndView getAllAccounts(ModelAndView mv) {
		mv.addObject("accounts", as.getAllAccounts());
		mv.setViewName("allAccount");
		return mv;
	}

	@GetMapping(path = "addAccount")
	public String addAccountGet(Model model) {
		model.addAttribute("account", new FormBean());
		return "addAccount";
	}

	@PostMapping(path = "addAccount")
	public String addAccountPost(FormBean formBean) {
		Customer customer=new Customer(formBean.getName(),formBean.getEmail(),formBean.getPhone(),
				formBean.getAddress(),formBean.getCity(),formBean.getCountry());
		Account account=new Account(formBean.getBalance(),customer,formBean.isBlocked());
		customer.setAccount(account);
		as.createAccount(account);
		return "redirect:allAccount";

	}

	@RequestMapping(value = "transfer", method = RequestMethod.GET)
	public String transferGet(Model model) {
		model.addAttribute("accountForm", new AccountForm());
		return "fund_transfer";

	}

	@RequestMapping(value = "withdraw", method = RequestMethod.GET)
	public String withdrawGet(Model model) {
		model.addAttribute("moneyForm", new MoneyForm());
		return "withdraw_form";

	}

	@RequestMapping(value = "withdraw", method = RequestMethod.POST)
	public String withdrawPost(@Valid @ModelAttribute(value = "moneyForm") MoneyForm moneyForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "withdraw_form";
		}
		accservice.withdraw(moneyForm.getFromAccount(), moneyForm.getAmount());

		return "redirect:allAccount";
	}

	@RequestMapping(value = "deposit", method = RequestMethod.GET)
	public String depositGet(Model model) {
		model.addAttribute("moneyForm", new MoneyForm());
		return "deposit_form";

	}

	@RequestMapping(value = "deposit", method = RequestMethod.POST)
	public String depositPost(@Valid @ModelAttribute(value = "moneyForm") MoneyForm moneyForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "deposit_form";
		}
		accservice.deposit(moneyForm.getFromAccount(), moneyForm.getAmount());

		return "redirect:allAccount";
	}

	@RequestMapping(value = "transfer", method = RequestMethod.POST)
	public String transferPost(@Valid @ModelAttribute(value = "accountForm") AccountForm accountForm,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "fund_transfer";
		}
		accservice.transfer(accountForm.getFromAccount(), accountForm.getToAccount(), accountForm.getAmount());
		return "redirect:allAccount";

	}
}
