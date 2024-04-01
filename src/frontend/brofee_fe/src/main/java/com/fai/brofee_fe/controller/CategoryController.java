package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.CategoryCreateDTO;
import com.fai.brofee_fe.dto.CategoryDTO;
import com.fai.brofee_fe.entity.CategoryRevenue;
import com.fai.brofee_fe.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping("")
    public String getCategoryPagination(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(required = false, defaultValue = "") String searchKeyword
    )
    {
        List<CategoryRevenue> categoryPagination = categoryService.getCategoryPagination(page, size, searchKeyword);
        Integer totalPage = categoryPagination.getFirst().getTotal_pages();
        model.addAttribute("totalPages", totalPage);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page+1);

        model.addAttribute("categories", categoryPagination);
        model.addAttribute("newCategory", new CategoryCreateDTO());

        return "categoryPage/index";
    }

    // SAVE NEW CATEGORY
    @PostMapping("/add")
    public String saveCategory(@Valid @ModelAttribute("newCategory") CategoryCreateDTO newCategory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "categoryPage/index";
        } else {
            categoryService.createNewCategory(newCategory);
            return "redirect:/categories";
        }
    }

    // DELETE
    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

}