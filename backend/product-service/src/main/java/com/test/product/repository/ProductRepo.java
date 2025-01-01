package com.test.product.repository;

import com.test.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo  extends MongoRepository<Product, String> {

}
