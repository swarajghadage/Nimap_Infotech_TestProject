package com.nt.dto;

import com.nt.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDTO {

	private int id;
	
	private String name;
	
	private  String description;
	
	private Double price;
	
	private  Category category;
}
