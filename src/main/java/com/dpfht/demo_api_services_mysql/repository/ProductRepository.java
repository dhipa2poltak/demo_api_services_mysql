package com.dpfht.demo_api_services_mysql.repository;

import com.dpfht.demo_api_services_mysql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
