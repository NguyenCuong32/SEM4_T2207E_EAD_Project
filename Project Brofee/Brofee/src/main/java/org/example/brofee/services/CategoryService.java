package org.example.brofee.services;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.CategoryDto;
import org.example.brofee.entities.Category;
import org.example.brofee.repositories.ICategoryRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.get();
    }

    @Override
    public Category saveCategory(CategoryDto category) {
        Category setCategory = new Category();
        setCategory.setNameCategory(category.getNameCategory());
        setCategory.setStatus(category.getStatus());
        return  categoryRepository.save(setCategory);
    }

    @Override
    public Category updateCategory(UUID id, CategoryDto category) {
        Category findCategory = getCategoryById(id);
        findCategory.setNameCategory(category.getNameCategory());
        findCategory.setStatus(category.getStatus());
        return categoryRepository.saveAndFlush(findCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
