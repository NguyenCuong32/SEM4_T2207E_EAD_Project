package org.example.brofee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @GetMapping("/list")
    public String listCategory(){
        return "Brofee/html/categories/listcategory";
    }

    @GetMapping("/add")
    public String addCategory(){
        return "Brofee/html/categories/addcategory";
    }
}
