package edu.poly.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.domain.Product;
import edu.poly.shop.service.ProductService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Product> list = productService.findByStatus(3);
		model.addAttribute("listProducts", list);
		return "/fragments/index";
	}
}
