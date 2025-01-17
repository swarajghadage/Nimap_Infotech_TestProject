package com.nt.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}
