package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Order;
import edu.poly.shop.model.OrderDto;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.ShoppingCartService;

@Controller
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	ShoppingCartService cart;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("")
	public String order(Model model) {
		
		String email = String.valueOf(session.getAttribute("username"));
		
		Customer cus = customerService.findByEmail(email);
		
		Order oht = new Order();
		oht.setPhoneNumber(cus.getPhone());
		oht.setEmail(email);
		oht.setTotalMoney(shoppingCartService.getAmount());
		
		System.out.println("name: "+cus.getName());
		model.addAttribute("yourname",cus.getName());
		
		model.addAttribute("order",oht);
		
		model.addAttribute("cart", cart.getItems());
		return "/admin/products/order";
	}
	
	@PostMapping("")
	public String order(Model model, @Valid @ModelAttribute("order") OrderDto dto, BindingResult result) {
		return "";
	}
}
