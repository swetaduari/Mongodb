package com.example.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.model.Product;

public interface ProductRepo
        extends MongoRepository<Product, String> {
}