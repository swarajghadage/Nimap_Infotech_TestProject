package com.nt.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nt.Service.CategoryService;
import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.mapper.CategoryMapper;
import com.nt.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Category createCategory(CategoryRequestDTO dto) {

		Category addedCategory = categoryRepository.save(categoryMapper.toEntity(dto));
		if (addedCategory != null) {
			return addedCategory;
		} else {
			return null;
		}

	}

	@Override
	public Category getById(int id) throws CategoryNotFoundException {

		Optional<Category> getCategory = categoryRepository.findById(id);

		if (getCategory.isPresent()) {

			return getCategory.get();
		} else {

			throw new CategoryNotFoundException("Category with this Id is not present", HttpStatus.EXPECTATION_FAILED);
		}

	}

	@Override
	public Category updateCategory(Category category, int id) {
		category.setId(id);
		Category updatedCategory = categoryRepository.save(category);
		return updatedCategory;

	}

	@Override
	public Category deleteCategory(int id)throws CategoryNotFoundException {
	
		Optional<Category> deletedCategory =categoryRepository.findById(id);
		
		if (deletedCategory.isPresent()) {

			categoryRepository.deleteById(id);
			return deletedCategory.get();
		} else {

			throw new CategoryNotFoundException("Category with this Id is not present", HttpStatus.EXPECTATION_FAILED);
		}
		
	}

	@Override
	public List<Category> getAllCategoriByPagination(int page, int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		
		Page<Category> pageList =categoryRepository.findAll(pageable);
		List<Category> list = new ArrayList<>();
		for (Category c : pageList) {
			list.add(c);

		}

		return list;
	}
	
	
	

	
	
}
