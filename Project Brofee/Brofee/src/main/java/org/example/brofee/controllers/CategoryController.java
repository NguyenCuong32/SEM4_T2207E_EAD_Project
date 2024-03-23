package org.example.brofee.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.CategoryDto;
import org.example.brofee.entities.Category;
import org.example.brofee.repositories.ICategoryRepository;
import org.example.brofee.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ICategoryRepository categoryRepository;

    @GetMapping("/list")
    public String listCategory(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "html/category/Category";
    }

    @GetMapping("/add")
    public String addCategory(Model model){
        CategoryDto category = new CategoryDto();
        model.addAttribute("category",category);
        return "html/category/CreateCategory";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") @Valid CategoryDto category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Có lỗi validation, xử lý thông báo lỗi
            return "html/category/CreateCategory";
        }

        Category findNameCategory = categoryRepository.findCategoryByNameCategory(category.getNameCategory());
        if (findNameCategory != null) {
            model.addAttribute("errorMessage", "Name Category already exists");
            return "html/category/CreateCategory";
        } else {
            categoryService.saveCategory(category);
            return "redirect:/category/list";
        }
    }

    @GetMapping("/edit")
    public String editCategory(@RequestParam("id") UUID id, Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category",category);
        return "html/category/EditCategory";
    }

    @PostMapping("/update")
    public String updateCategory(@RequestParam("id") UUID id, @ModelAttribute("category") @Valid CategoryDto category, BindingResult bindingResult, Model model) {
        Category findCategory = categoryService.getCategoryById(id);

        if (bindingResult.hasErrors()) {
            // Có lỗi validation, xử lý thông báo lỗi
            return "html/category/EditCategory";
        }

        if (findCategory == null) {
            model.addAttribute("errorMessage", "Category not found");
            return "html/category/EditCategory";
        }

        Category findNameCategory = categoryRepository.findCategoryByNameCategory(category.getNameCategory());
        if (findNameCategory != null && !findNameCategory.getId().equals(id)) {
            model.addAttribute("errorMessage", "Category name already exists");
            return "html/category/EditCategory";
        }

        categoryService.updateCategory(id, category);
        return "redirect:/category/list";
    }

    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id")UUID id,Model model){
        Category findCategory = categoryService.getCategoryById(id);
        if (findCategory == null) {
            model.addAttribute("errorMessage", "Category not found");
            return "html/category/EditCategory";
        }
        else{
            categoryService.deleteCategory(id);
            return "redirect:/category/list";
        }
    }
}
