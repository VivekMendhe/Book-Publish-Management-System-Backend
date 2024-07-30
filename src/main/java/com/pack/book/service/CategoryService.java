package com.pack.book.service;

import java.util.List;

import com.pack.book.dto.CategoryDTO;

public interface CategoryService {
	CategoryDTO addCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryById(Long id);

	CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

	void deleteCategory(Long id);
}
