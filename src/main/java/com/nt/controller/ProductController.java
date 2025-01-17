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

import com.nt.Service.ProductService;
import com.nt.dto.ProductRequestDTO;
import com.nt.dto.ProductResponseDTO;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@RestController
@RequestMapping("api/products")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<String> create(@Valid @RequestBody ProductRequestDTO dto) throws CategoryNotFoundException {

		ProductResponseDTO createdProduct = productService.createProduct(dto);

		if (createdProduct != null) {

			return ResponseEntity.status(HttpStatus.CREATED).body("Product created Sucessfully");

		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Error Occurs");

		}

	}

	@PutMapping("{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductRequestDTO dto) {

		ProductResponseDTO updateResp = productService.updateProduct(id, dto);

		if (updateResp != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Product Updated Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Not Updated");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) throws ProductNotFoundException {

		ProductResponseDTO resDto = productService.deleteById(id);
		if (resDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Product Deleted Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Error Occured");
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductResponseDTO> getById(@PathVariable int id) throws ProductNotFoundException {

		ProductResponseDTO dto = productService.getById(id);

		if (dto != null) {

			return ResponseEntity.status(HttpStatus.CREATED).body(dto);

		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@GetMapping
	public ResponseEntity<?> getAllProduct(@RequestParam int page, @RequestParam int size) {

		List<ProductResponseDTO> list = productService.getAllProductByPagination(page, size);

		if (list != null) {

			return ResponseEntity.status(HttpStatus.OK).body(list);
		} else {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Products Not Found /Something Went Wrong");
		}
	}

}
