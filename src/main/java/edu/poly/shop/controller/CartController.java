package edu.poly.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.shop.service.ShoppingCartService;


@Controller
public class CartController {
	@Autowired
	ShoppingCartService cart;
	
	@RequestMapping("/cart/view")
	public String view(Model model) {
		
	model.addAttribute("cart", cart.getItems());
	
	return "/admin/products/cart";
	}
	
	@RequestMapping("/cart/add/{id}")
	public String add(@PathVariable("id") Integer id) {
	cart.add(id);
	return "redirect:/cart/view"; // hiển thị giỏ hàng
	}
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
	cart.remove(id);
	return "redirect:/cart/view";
	}
	@RequestMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, 
	@RequestParam("quantity") Integer qty) {
	cart.update(id, qty);
	return "redirect:/cart/view";
	}
	@RequestMapping("/cart/clear")
	public String clear() {
	cart.clear();
	return "redirect:/cart/view";
	}
}
