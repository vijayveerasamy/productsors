package com.vijay.demo.productsors.repository;

import com.vijay.demo.productsors.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}