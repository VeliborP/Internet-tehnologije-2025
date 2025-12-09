package com.internet_tehnologije.demo.controllers;

import com.internet_tehnologije.demo.model.Category;
import com.internet_tehnologije.demo.model.Product;
import com.internet_tehnologije.demo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String list(Model model) {
        List<Category> products = categoryService.findAll();
        model.addAttribute("categories", products);
        model.addAttribute("pageTitle", "Categories List");

        model.addAttribute("fragment", "categories/list");
        return "layouts/main";
    }

}
