package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="product")
public class Product {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;

	    @Column(nullable = false)
	    private String name;

	    private String description;

	    private Double price;

	    @ManyToOne
	    @JoinColumn(name = "category_id", nullable = false)
	    private Category category;

}
