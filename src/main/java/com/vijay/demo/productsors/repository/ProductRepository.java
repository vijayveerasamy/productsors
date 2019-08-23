package com.vijay.demo.productsors.repository;

import com.vijay.demo.productsors.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);

}