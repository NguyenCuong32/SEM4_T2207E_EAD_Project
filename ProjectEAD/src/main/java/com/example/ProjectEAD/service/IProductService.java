package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProduct();
    Optional<Product> getProductById(Integer id);
    void add(Product product);
    void deleteById(Integer id);
}
