package com.nt.mapper;

import org.springframework.stereotype.Component;

import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;

@Component
public class CategoryMapper {

	public Category toEntity(CategoryRequestDTO dto) {

		return new Category(dto.getId(), dto.getName(),dto.getDescription(), null);
	}

}
