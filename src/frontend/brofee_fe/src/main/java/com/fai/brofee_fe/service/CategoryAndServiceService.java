package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.CategoryDetailDTO;
import com.fai.brofee_fe.entity.Category;
import com.fai.brofee_fe.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryAndServiceService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public List<CategoryDetailDTO> getAllCategoriesWithDetail() {
        List<Category> categories = categoryRepository.findAll();
        categories.forEach(Category::getServices);
        return categories
                .stream()
                .map(category -> modelMapper.map(category, CategoryDetailDTO.class))
                .toList();
    }

}
