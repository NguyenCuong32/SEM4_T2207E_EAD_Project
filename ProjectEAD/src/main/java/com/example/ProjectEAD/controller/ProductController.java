package com.example.ProjectEAD.controller;

import com.example.ProjectEAD.entity.Category;
import com.example.ProjectEAD.entity.Product;
import com.example.ProjectEAD.service.CategoryService;
import com.example.ProjectEAD.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("listProduct")
    public String List(Model model){
        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "admin/product/index";
    }
    @GetMapping("addForm")
    public String addForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        return "admin/product/add";
    }
    @PostMapping("add")
    public String save(@Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/product/add";
        }
        productService.add(product);
        return "redirect:/admin/product/listProduct";
    }

    @GetMapping("editForm/{id}")
    public String editForm(@PathVariable int id, Model model){
        Optional<Product> productOptional = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            model.addAttribute("product", product);
            return "admin/product/edit";
        }
        return "redirect:/admin/product/listProduct";
    }
    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/product/edit";
        }
        productService.add(product);
        return "redirect:/admin/product/listProduct";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        productService.deleteById(id);
        return "redirect:/admin/product/listProduct";
    }
}
