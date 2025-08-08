package com.dpfht.demo_api_services_mysql.ProductService.repository;

import com.dpfht.demo_api_services_mysql.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
