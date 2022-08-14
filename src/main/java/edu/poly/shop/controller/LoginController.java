package edu.poly.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.CookieService;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CookieService cookieService;
	
	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("customer", new CustomerDto());
		return "/admin/accounts/loginc";
	}
	
	@PostMapping("")
	public String login(ModelMap model, @ModelAttribute("customer") CustomerDto dto) {
		if(dto.getEmail().equals("") || dto.getPassword().equals("")) {
			model.addAttribute("message", "email and password not is empty");
			return "admin/accounts/loginc";
		}
		Customer account = customerService.login(dto.getEmail(), dto.getPassword());
		
		if(account == null) {
			model.addAttribute("message", "Invalid email and pasword");
			return "admin/accounts/loginc";
		}
		session.setAttribute("username", account.getEmail());
		
//		Object ruri = session.getAttribute("redirect-uri");
		
//		if(ruri != null) {
//			session.removeAttribute("redirect-uri");
//			return new ModelAndView("redirect:" + ruri);
//		} 
//		
		return "redirect:/home";
	}
	
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("customer", new CustomerDto());
		return "/admin/accounts/register";
	}
	
	@PostMapping("register")
	public ModelAndView register(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result) {
		
		if(result.hasErrors()) {
			model.addAttribute("message","fields cannot be left blank, email must be in the correct format");
			return new ModelAndView("/admin/accounts/register",model);
		}
//		System.out.println("pass: "+ dto.getPassword());
//		System.out.println("REpass: "+ request.getParameter("repassword"));
		if(!dto.getPassword().equals(request.getParameter("repassword"))){
			model.addAttribute("message","password does not match");
			return new ModelAndView("admin/accounts/register",model);
		}
		
		Customer cus = new Customer();
		BeanUtils.copyProperties(dto, cus);
		cus.setStatus("activate");
		
		customerService.save(cus);
		
		model.addAttribute("message", "Register successful!");
		
		return new ModelAndView("admin/accounts/loginc",model);
	}
}
