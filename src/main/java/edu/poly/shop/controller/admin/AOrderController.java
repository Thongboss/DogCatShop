package edu.poly.shop.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.service.OrderService;

@Controller
@RequestMapping("admin/order")
public class AOrderController {
	@Autowired
	OrderService  orderService;
	@Autowired
	HttpServletRequest request;
	
	@GetMapping("")
	public String order(Model model) {
		model.addAttribute("listOrder", orderService.getListOrderByStatus("Chờ xác nhận"));
		return "/admin/accounts/aorder";
	}
	
	@ModelAttribute("liststatus")
	public List<String> list(){
		List<String> status = new ArrayList<>();
		status.add("Chờ xác nhận");
		status.add("Đang giao");
		status.add("Đã nhận");
		status.add("Hủy đơn");
		return status;
	}
	
	@PostMapping("")
	public String orderb(Model model) {
		
		return "forward:/admin/order";
	}
}
