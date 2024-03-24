package com.example.ProjectEAD.controller;

import com.example.ProjectEAD.entity.Category;
import com.example.ProjectEAD.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("list")
    public String List(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "admin/category/index";
    }

    @GetMapping("addForm")
    public String addForm(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/category/add";
    }

    @PostMapping("add")
    public String save(@Valid @ModelAttribute Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/category/add";
        }
        categoryService.add(category);
        return "redirect:/admin/category/list";
    }

    @GetMapping("editForm/{id}")
    public String editForm(@PathVariable int id, Model model){
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            model.addAttribute("category", category);
            return "admin/category/edit";
        }
        return "redirect:/admin/category/list";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute Category category, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/category/edit";
        }
        categoryService.add(category);
        return "redirect:/admin/category/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        categoryService.deleteById(id);
        return "redirect:/admin/category/list";
    }
}
