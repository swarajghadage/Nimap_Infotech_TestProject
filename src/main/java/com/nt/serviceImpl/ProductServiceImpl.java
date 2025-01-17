package com.nt.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nt.Service.CategoryService;
import com.nt.Service.ProductService;
import com.nt.dto.ProductRequestDTO;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.entity.Product;
import com.nt.exception.CategoryNotFoundException;
import com.nt.exception.ProductNotFoundException;
import com.nt.mapper.ProductMapper;
import com.nt.repository.CategoryRepository;
import com.nt.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductResponseDTO createProduct(@Valid ProductRequestDTO dto) throws CategoryNotFoundException {

		Category category = categoryService.getById(dto.getCategoryId());

		Product product = productMapper.toEntity(dto);
		product.setCategory(category);

		Product addedProduct = productRepository.save(product);

		return productMapper.toResponseDto(addedProduct);

	}

	@Override
	public ProductResponseDTO getById(int id) throws ProductNotFoundException {
		Optional<Product> getproduct = productRepository.findById(id);

		if (getproduct.isPresent()) {

			return productMapper.toResponseDto(getproduct.get());

		} else {

			throw new ProductNotFoundException("Product With This Id does not Exists", HttpStatus.NOT_FOUND);

		}

	}

	@Override
	public ProductResponseDTO updateProduct(int id, ProductRequestDTO dto) {
		if (!categoryRepository.existsById(dto.getCategoryId())) {
			throw new RuntimeException("Invalid category ID: " + dto.getCategoryId());
		}
		dto.setId(id);
		Product updatedProduct = productRepository.save(productMapper.toEntity(dto));
		return productMapper.toResponseDto(updatedProduct);
	}

	@Override
	public ProductResponseDTO deleteById(int id) throws ProductNotFoundException {
		Optional<Product> deletedProduct = productRepository.findById(id);

		if (deletedProduct.isPresent()) {

			ProductResponseDTO dto = getById(id);

			productRepository.deleteById(id);

			return dto;
		} else {

			throw new ProductNotFoundException("Product with this Id is not present", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProductByPagination(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> list = productRepository.findAll(pageable);

		List<ProductResponseDTO> listDto = new ArrayList<>();
		for (Product p : list) {
			listDto.add(productMapper.toResponseDto(p));
		}

		return listDto;

	}

}
