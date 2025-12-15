package com.internet_tehnologije.demo.controllers;

import com.internet_tehnologije.demo.model.Category;
import com.internet_tehnologije.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "Categories List");

        model.addAttribute("fragment", "categories/list");
        return "layouts/main";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);

        model.addAttribute("action", "Create");
        model.addAttribute("fragment", "categories/form");
        return "layouts/main";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("action", category.getId() == null ? "Create" : "Update");
            model.addAttribute("fragment", "categories/form");
            return "layouts/main";
        }

        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var opt = categoryService.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/categories";
        }

        model.addAttribute("category", opt.get());
        model.addAttribute("action", "Update");
        model.addAttribute("fragment", "categories/form");

        return "layouts/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        var categoryOpt = categoryService.findById(id);
        if (categoryOpt.isEmpty()) {
            ra.addFlashAttribute("error", "Category not found.");
            return "redirect:/categories";
        }

        var category = categoryOpt.get();

        if (!category.getProducts().isEmpty()) {
            ra.addFlashAttribute("error", "Category cannot be deleted because it has products.");
            return "redirect:/categories";
        }

        categoryService.deleteById(id);
        ra.addFlashAttribute("success", "Category deleted successfully.");
        return "redirect:/categories";
    }
}
