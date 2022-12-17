package edu.poly.shop.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.poly.shop.domain.Order;
import edu.poly.shop.model.OrderDto;
import edu.poly.shop.service.MailerService;
import edu.poly.shop.service.OrderService;

@Controller
@RequestMapping("admin/order")
public class AOrderController {
	@Autowired
	OrderService  orderService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	MailerService mailerService;
	
	@GetMapping("")
	public String order(Model model) {
		model.addAttribute("listOrder", orderService.getListOrderByStatus("Chờ xác nhận"));
		return "admin/accounts/aorder";
	}
	
	@RequestMapping("/{id}")
	public String updateOder(Model model, @PathVariable("id") Integer id, @ModelAttribute OrderDto odto) {
		Long convert = (long) id;
		Order mainn = orderService.findById(convert).get();
		String status = "";
		String code = mainn.getCodeOrder();
		String email = mainn.getEmail();
		String subject = "Đơn hàng "+code+" từ DogCatShop!";
//		String statusOld = orderService.findById(convert).get().getStatus();
		
		if(odto.getStatus().equals("Đang giao") ) {
			status = "Đơn hàng của bạn đã được chuyển đi.";
		}else {
			status = "Đơn hàng của bạn đã bị hủy bỏ do thiếu tính xác thực.";
		}
		
		orderService.updateOrderSetStatusForId(odto.getStatus(), convert);
		mailerService.queue(email, subject, status);
		
		return "redirect:/admin/order";
	}
	
	@GetMapping("/start")
	public String orderStart(Model model) {
		model.addAttribute("listOrderStart", orderService.getListOrderByStatus("Đang giao"));
		return "admin/accounts/Ostart";
	}
	
	@RequestMapping("/start/{id}")
	public String updateOderStart(Model model, @PathVariable("id") Integer id, @ModelAttribute OrderDto odto) {
		Long convert = (long) id;
		Order mainn = orderService.findById(convert).get();
		String status = "";
		String code = mainn.getCodeOrder();
		String email = mainn.getEmail();
		String subject = "Đơn hàng "+code+" từ DogCatShop!";
//		String statusOld = orderService.findById(convert).get().getStatus();
		
		if(odto.getStatus().equals("Đã nhận") ) {
			status = "Đơn hàng của bạn đã được giao. Chúc bạn có những trải nghiệm tuyệt vời cùng thú cưng của mình!";
		}else {
			status = "Đơn hàng của bạn đã bị hủy bỏ do gặp vấn đề trong lúc chuyển giao. Chúng tối sẽ liên hệ lại sớm nhất với bạn.";
		}
		
		orderService.updateOrderSetStatusForId(odto.getStatus(), convert);
		mailerService.queue(email, subject, status);
		
		return "redirect:/admin/order/start";
	}
	
	@ModelAttribute("liststatus")
	public List<String> list(){
		List<String> status = new ArrayList<>();
		status.add("Chờ xác nhận");
		status.add("Đang giao");
//		status.add("Đã nhận");
		status.add("Hủy đơn");
		return status;
	}
	
	@ModelAttribute("liststatusstart")
	public List<String> listStart(){
		List<String> status = new ArrayList<>();
//		status.add("Chờ xác nhận");
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
