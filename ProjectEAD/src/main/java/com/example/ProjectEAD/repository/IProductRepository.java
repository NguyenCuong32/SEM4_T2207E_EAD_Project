package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
