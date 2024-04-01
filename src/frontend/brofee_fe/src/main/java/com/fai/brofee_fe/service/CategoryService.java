package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.CategoryCreateDTO;
import com.fai.brofee_fe.dto.CategoryDTO;
import com.fai.brofee_fe.dto.CategoryEditDTO;
import com.fai.brofee_fe.entity.Category;
import com.fai.brofee_fe.entity.CategoryRevenue;
import com.fai.brofee_fe.repository.CategoryRepository;
import com.fai.brofee_fe.repository.CategoryRepository_SP;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@Transactional
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryRepository_SP categoryRepository_SP;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return Collections.emptyList();
        }

        return categoryList.stream().map(category ->
            modelMapper.map(category, CategoryDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<CategoryRevenue> getCategoryPagination(int page, int size, String searchKeyword) {
        return categoryRepository_SP.getCategoryRevenue(page, size, searchKeyword);
    }

    @Override
    public Optional<CategoryDTO> getCategoryDTOById(Long id) {
        return categoryRepository.findById(id).map(
                category -> modelMapper.map(category, CategoryDTO.class)
        );
    }

    @Override
    public Optional<CategoryEditDTO> getCategoryEditById(Long id) {
        return categoryRepository.findById(id).map(category -> {
                    CategoryEditDTO categoryEditDTO = modelMapper.map(category, CategoryEditDTO.class);
                    categoryEditDTO.setOld_icon(category.getIcon());

                    return categoryEditDTO;
                }
        );
    }

    @Override
    public void createNewCategory(CategoryCreateDTO categoryCreateDTO) {
        try {
            String iconUrl = null;
            if (categoryCreateDTO.getIcon() != null) {
                iconUrl = cloudinaryService.upload(categoryCreateDTO.getIcon());
            }
            Category newCategory = modelMapper.map(categoryCreateDTO, Category.class);
            newCategory.setIcon(iconUrl);

            newCategory.setCreatedAt(LocalDateTime.now());
            categoryRepository.save(newCategory);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to upload image: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateCategory(Long id, CategoryEditDTO categoryEditDTO) {
        Optional<Category> existedCategory = categoryRepository.findById(id);
        existedCategory.ifPresentOrElse(
                category -> {
                    modelMapper.map(categoryEditDTO, category);

                    String iconUrl = updateCategoryIcon(categoryEditDTO);
                    category.setIcon(iconUrl);

                    category.setUpdatedAt(LocalDateTime.now());
                    categoryRepository.save(category);
                },
                () -> {
                    // Xử lý nếu sinh viên không tồn tại
                    Category newCategory = modelMapper.map(categoryEditDTO, Category.class);
                    newCategory.setId(id);

                    String iconUrl = updateCategoryIcon(categoryEditDTO);
                    newCategory.setIcon(iconUrl);

                    newCategory.setCreatedAt(LocalDateTime.now());
                    categoryRepository.save(newCategory);
                }
        );
    }

    private String updateCategoryIcon(CategoryEditDTO categoryEditDTO) {
        try {
            String iconUrl = categoryEditDTO.getOld_icon();
            if (categoryEditDTO.getNew_icon() != null) {
                iconUrl = cloudinaryService.upload(categoryEditDTO.getNew_icon());
            }
            return iconUrl;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to upload image: " + ex.getMessage(), ex);
        }

    }


    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
