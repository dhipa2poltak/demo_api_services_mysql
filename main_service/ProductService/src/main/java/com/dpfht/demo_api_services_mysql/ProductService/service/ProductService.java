package com.dpfht.demo_api_services_mysql.ProductService.service;


import com.dpfht.demo_api_services_mysql.ProductService.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
