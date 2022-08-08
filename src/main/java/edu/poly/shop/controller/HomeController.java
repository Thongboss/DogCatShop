package edu.poly.shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.shop.domain.Product;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.StorageService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Product> list = productService.findByStatus("Bestseller");
		model.addAttribute("listProducts", list);
		return "/admin/fragments/index";
	}
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	@GetMapping("detail/{productId}")
	public String detail(Model model, @PathVariable("productId") Long id) {
		Optional<Product> pro = productService.findById(id);
		
		ProductDto dto = new ProductDto();
		
		BeanUtils.copyProperties(pro.get(), dto);
		
		model.addAttribute("product",dto);
		
		return "admin/products/view";
	}
}
