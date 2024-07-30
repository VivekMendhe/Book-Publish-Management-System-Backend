package com.pack.book.serviceimpl;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.book.dto.CategoryDTO;
import com.pack.book.model.Category;
import com.pack.book.repository.CategoryReository;
import com.pack.book.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	@Autowired
	private CategoryReository categoryReository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = this.dtoToEntity(categoryDTO);
		Category saveCategory = this.categoryReository.save(category);
		return this.entityToDTO(saveCategory);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return this.categoryReository.findAll().stream().map(this::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryById(Long id) {
		Category category = this.categoryReository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		return this.entityToDTO(category);
	}

	@Override
	public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
		Category existingCategory = this.categoryReository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		modelMapper.map(categoryDTO, existingCategory);
		Category saveCategory = this.categoryReository.save(existingCategory);
		return this.entityToDTO(saveCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		if (this.categoryReository.existsById(id)) {
			this.categoryReository.deleteById(id);
		} else {
			throw new ResolutionException("Category not founds");
		}
	}

	// covert dto to entity
	public Category dtoToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, Category.class);
	}

	// covert entity to dto
	public CategoryDTO entityToDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}

}
