package com.nt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Service.CategoryService;
import com.nt.dto.APIResponseDto;
import com.nt.dto.CategoryRequestDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<APIResponseDto> create(@Valid @RequestBody CategoryRequestDTO dto) {

		Category createdCategory = categoryService.createCategory(dto);

		if (createdCategory != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new APIResponseDto(dto, "Category Created Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponseDto(null, "Some Error Occured"));

		}

	}
	
	@GetMapping("{id}")
	public ResponseEntity<APIResponseDto> getDataById(@PathVariable int id  ) throws CategoryNotFoundException{
		
		Category  getCategory =categoryService.getById(id);
		
		if (getCategory!=null) {
			
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new APIResponseDto(getCategory,"Category Got Sucessful"));
		}else {
			
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new APIResponseDto(null,"Data not Found"));
		}
	}
	
	

}
