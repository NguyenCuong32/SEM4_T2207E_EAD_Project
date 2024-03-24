package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
