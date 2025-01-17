package com.nt.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;

@Service
public interface CategoryService {

	
	Category createCategory(CategoryRequestDTO dto);

	Category getById(int id) throws CategoryNotFoundException;

	Category updateCategory(Category category, int id);

	Category deleteCategory(int id) throws CategoryNotFoundException;

	List<Category> getAllCategoriByPagination(int page, int size);

	

}
