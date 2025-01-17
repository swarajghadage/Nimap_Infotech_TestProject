package com.nt.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.nt.dto.ProductRequestDTO;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;

@Service
public interface ProductService {

	ProductResponseDTO createProduct(@Valid ProductRequestDTO dto) throws CategoryNotFoundException;

	ProductResponseDTO getById(int id) throws ProductNotFoundException;

	ProductResponseDTO updateProduct(int id, ProductRequestDTO dto);

	ProductResponseDTO deleteById(int id) throws ProductNotFoundException;

	List<ProductResponseDTO> getAllProductByPagination(int page, int size);

}
