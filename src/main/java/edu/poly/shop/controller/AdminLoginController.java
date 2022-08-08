package edu.poly.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
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
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CookieService;

@Controller
@RequestMapping("alogin")
public class AdminLoginController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private CookieService cookieService;
	
	@GetMapping("")
	public String login(Model model) {
		model.addAttribute("account", new AdminLoginDto());
		return "/admin/accounts/login";
	}
	
	@PostMapping("")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") AdminLoginDto dto, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("message", "username and password not is empty");
			return new ModelAndView("/admin/accounts/login", model);
		}
		Account account = accountService.login(dto.getUsername(), dto.getPassword());
		
		if(account == null) {
			model.addAttribute("message", "Invalid username and pasword");
			return new ModelAndView("/admin/accounts/login", model);
		}
		session.setAttribute("username", account.getUsername());
		if(dto.getRememberMe() == true) {
			cookieService.add(account.getUsername(),account.getPassword(), 10);
		}else {
			cookieService.remove(account.getUsername());
		}
		
		Object ruri = session.getAttribute("redirect-uri");
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:"+ruri);
		}
		
		
		return new ModelAndView("forward:/fragments/index", model);
		
	}
}
