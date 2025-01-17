package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDTO {

	private int id;

	private String name;

	private String description;

	private int quantity; 
	
	private Double price;

	private int categoryId;
}
