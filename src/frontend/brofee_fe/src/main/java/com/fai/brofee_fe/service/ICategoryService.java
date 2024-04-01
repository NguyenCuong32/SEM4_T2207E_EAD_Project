package com.fai.brofee_fe.service;


import com.fai.brofee_fe.dto.CategoryCreateDTO;
import com.fai.brofee_fe.dto.CategoryDTO;
import com.fai.brofee_fe.dto.CategoryEditDTO;
import com.fai.brofee_fe.entity.CategoryRevenue;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICategoryService {

    List<CategoryDTO> getAllCategories();

    List<CategoryRevenue> getCategoryPagination(int page, int size, String searchKeyword);

    Optional<CategoryDTO> getCategoryDTOById (Long id);

    Optional<CategoryEditDTO> getCategoryEditById (Long id);

    void createNewCategory(CategoryCreateDTO categoryCreateDTO);

    void updateCategory(Long id, CategoryEditDTO CategoryEditDTO);

    void deleteCategory(Long id);

}
