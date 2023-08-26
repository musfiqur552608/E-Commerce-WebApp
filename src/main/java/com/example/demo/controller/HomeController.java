package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "home"})
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping({"/shop"})
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		return "shop";
	}
	
	@GetMapping({"/shop/category/{id}"})
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategory(id));
		return "shop";
	}
	
}
