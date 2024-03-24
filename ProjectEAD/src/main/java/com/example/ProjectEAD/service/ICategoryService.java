package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategory();
    Optional<Category> getCategoryById(Integer id);
    void add(Category category);
    void deleteById(Integer id);
}
