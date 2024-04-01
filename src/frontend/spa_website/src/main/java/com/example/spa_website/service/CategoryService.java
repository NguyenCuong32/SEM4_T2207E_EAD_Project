package com.example.spa_website.service;

import com.example.spa_website.dto.CategoryDTO;
import com.example.spa_website.dto.CategoryDetailDTO;
import com.example.spa_website.entity.Category;
import com.example.spa_website.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findNoneDeleted();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public List<CategoryDetailDTO> getCategoriesWithDetails() {
        List<Category> categories = categoryRepository.findNoneDeleted();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryDetailDTO.class))
                .collect(Collectors.toList());
    }
}
