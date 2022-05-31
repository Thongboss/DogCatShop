package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDto());
		
		return "admin/categories/addOrEdit";
	}
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryDto dto = new CategoryDto();
		if(opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			model.addAttribute("category", dto);
			return new ModelAndView("admin/categories/addOrEdit", model);
		}
		model.addAttribute("message", "Category is not existed!");
		return new ModelAndView("forward:/admin/categories", model);
	} 
	@GetMapping("delete/{categoryId}")
	public String delete() {
		return "redirect:/admin/categories";
	}
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto, BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("/admin/categories/addOrEdit");
		}
		Category entity = new Category();
//		System.out.println(entity.getName());
		BeanUtils.copyProperties(dto, entity);
//		System.out.println(dto.getName());
		categoryService.save(entity);
//		System.out.println("con mia bug");
		model.addAttribute("message", "Category is saved!");
		return new ModelAndView("forward:/admin/categories", model);
	}
	@RequestMapping("")
	public String list(ModelMap model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("categories", list);
		return "admin/categories/list";
	}
	@GetMapping("search")
	public String search() {
		return "admin/categories/search";
	}
}
