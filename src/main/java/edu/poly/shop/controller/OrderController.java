package edu.poly.shop.controller;

import java.util.List;
import java.util.Optional;

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
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItemDto;
import edu.poly.shop.model.OrderDto;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.OrderDetailService;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.SessionService;
import edu.poly.shop.service.ShoppingCartService;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	ShoppingCartService cart;
	
	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	OrderService orderService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	HttpSession session;
	
//	@Autowired
//	SessionService sessionService;

	@GetMapping("")
	public String order(Model model) {

		String email = String.valueOf(session.getAttribute("username"));
		
//		System.out.println("email: "+email);

		Customer cus = customerService.findByEmail(email);
		
		if(cus == null) {
			return "redirect:/login";
		}

		Order oht = new Order();
		oht.setPhoneNumber(cus.getPhone());
		oht.setEmail(email);
		oht.setTotalMoney(shoppingCartService.getAmount());

//		System.out.println("name: " + cus.getName());
		model.addAttribute("yourname", cus.getName());

		model.addAttribute("order", oht);

		model.addAttribute("cart", cart.getItems());
		return "/admin/products/order";
	}

	@PostMapping("")
	public String order(Model model, @ModelAttribute("order") OrderDto dto) {
		Order ohu = new Order();
		ohu.setAddress(dto.getAddress());
		ohu.setEmail(dto.getEmail());
		ohu.setPhoneNumber(dto.getPhoneNumber());
		ohu.setStatus("Chờ xác nhận");
		ohu.setTotalMoney(shoppingCartService.getAmount());
//		System.out.println("total money: " + dto.getTotalMoney());
		List<Order> list = orderService.findAll();
		if (list.size() == 0) {
			ohu.setCodeOrder("HD001");
		} else {
			Long a;
			a = list.get(list.size() - 1).getOrderId();
			ohu.setCodeOrder("HD001" + String.valueOf(a));
		}
		String email = String.valueOf(session.getAttribute("username"));

		Customer cus = customerService.findByEmail(email);
		ohu.setCustomer(cus);
		
		orderService.save(ohu);
		
		Order oha = orderService.getOrderByCode(ohu.getCodeOrder());
		for (CartItemDto item : cart.getItems()) {
			OrderDetail oko = new OrderDetail();
			Optional<Product> pro = productService.findById(Long.parseLong(String.valueOf(item.getProductId())));
			oko.setOrder(oha);
			oko.setProduct(pro.get());
			oko.setQuantity(item.getQuantity());
			oko.setUnitPrice(item.getUnitPrice());
			
			orderDetailService.save(oko);
		}
		
		cart.clear();
		
//		session.removeAttribute("cart");
		
		return "redirect:/home";
	}
	
	@GetMapping("/history")
	public String history(Model model) {
		String email = String.valueOf(session.getAttribute("username"));

		Customer cus = customerService.findByEmail(email);
		
		List<Order> list = orderService.getListOrderHistory(cus.getCustomerId());
		
		model.addAttribute("orders", list);
		
		return "/admin/products/orderHistory";
	}
}
