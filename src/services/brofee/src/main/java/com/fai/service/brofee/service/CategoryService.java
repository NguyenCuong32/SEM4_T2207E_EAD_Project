package com.fai.service.brofee.service;

import com.fai.service.brofee.dto.CategoryDTO;
import com.fai.service.brofee.dto.CategoryDetailDTO;
import com.fai.service.brofee.entity.Category;
import com.fai.service.brofee.repository.CategoryRepository;
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
