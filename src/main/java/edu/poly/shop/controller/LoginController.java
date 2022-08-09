package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.CookieService;
import edu.poly.shop.service.CustomerService;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CookieService cookieService;
	
	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("account", new CustomerDto());
		return "/admin/accounts/loginc";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("customer", new CustomerDto());
		return "/admin/accounts/register";
	}
}
