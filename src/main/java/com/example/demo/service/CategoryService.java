package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepostory;
	public List<Category> getAllCategory(){
		return categoryRepostory.findAll();
	}
	public void addCategory(Category category) {
		categoryRepostory.save(category);
	}
}
