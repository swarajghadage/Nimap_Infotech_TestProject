package com.nt.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.dto.ProductRequestDTO;
import com.nt.dto.ProductResponseDTO;
import com.nt.entity.Category;
import com.nt.entity.Product;
import com.nt.repository.CategoryRepository;
@Component
public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public Product toEntity(ProductRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));
        
        return new Product(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getQuantity(),
            dto.getPrice(),
            category
        );
    }

    public ProductResponseDTO toResponseDto(Product product) {
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getCategory()
        );
    }
}
