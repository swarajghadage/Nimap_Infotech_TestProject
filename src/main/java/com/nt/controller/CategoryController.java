package com.nt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Service.CategoryService;
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
	public ResponseEntity<String> create(@Valid @RequestBody CategoryRequestDTO dto) {

		Category createdCategory = categoryService.createCategory(dto);

		if (createdCategory != null) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body( "Category Created Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body( "Something Went Wrong");

		}

	}
	

	@PutMapping("{id}")
	public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {

		Category updatedCategory = categoryService.updateCategory(category, id);

		if (updatedCategory != null) {

			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Category Updated Sucessfully");
		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Category Not Updated /Something Went Wrong");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) throws CategoryNotFoundException {

		Category isDeleted = categoryService.deleteCategory(id);
		
		if (isDeleted != null) {

			return ResponseEntity.status(HttpStatus.OK)
					.body( "Category Deleted Sucessfully");
		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body( "Category Not Deleted /Something Went Wrong");
		}

	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> getDataById(@PathVariable int id) throws CategoryNotFoundException {

	    Category getCategory = categoryService.getById(id);

	    if (getCategory != null) {
	        
	        return ResponseEntity.status( HttpStatus.OK ).body( getCategory );
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(null); 
	    }
	}

	
	@GetMapping
	public ResponseEntity<?> getAllCategories(@RequestParam int page, @RequestParam int size) {

	    List<Category> list = categoryService.getAllCategoriByPagination(page, size);

	    if (list != null && !list.isEmpty()) {
	       
	        return ResponseEntity.status(HttpStatus.OK).body(list);
	    } else {
	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("No categories found or something went wrong.");
	    }
	}


}
