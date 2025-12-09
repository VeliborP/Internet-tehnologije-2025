package com.internet_tehnologije.demo.controllers;
import com.internet_tehnologije.demo.model.Product;
import com.internet_tehnologije.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String list(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "Products List");

        model.addAttribute("fragment", "products/list");
        return "layouts/main";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Product p = new Product();
        p.setPrice(BigDecimal.ZERO);
        model.addAttribute("product", p);
        model.addAttribute("action", "Create");

        model.addAttribute("fragment", "products/form");
        return "layouts/main";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fragment", "products/form");
            return "layouts/main";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var opt = productService.findById(id);
        if (opt.isEmpty()) {
            return "redirect:/products";
        }
        model.addAttribute("product", opt.get());
        model.addAttribute("action", "Update");
        model.addAttribute("fragment", "products/form");

        return "layouts/main";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
