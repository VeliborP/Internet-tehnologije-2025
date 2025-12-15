package com.internet_tehnologije.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String indexPage(Model model) {
        List<String> items = List.of("Java", "Spring Boot", "Thymeleaf", "MySQL");

        model.addAttribute("technologies", items);
        model.addAttribute("pageTitle", "Products List");

        model.addAttribute("fragment", "index");
        return "layouts/main";
    }
}