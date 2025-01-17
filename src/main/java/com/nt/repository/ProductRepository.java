package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}
