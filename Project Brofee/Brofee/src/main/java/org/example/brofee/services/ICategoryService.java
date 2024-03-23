package org.example.brofee.services;

import org.example.brofee.dto.CategoryDto;
import org.example.brofee.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(UUID id);
    Category saveCategory(CategoryDto category);
    Category updateCategory(UUID id, CategoryDto category);
    void deleteCategory(UUID id);
}
