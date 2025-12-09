package com.internet_tehnologije.demo.service;

import com.internet_tehnologije.demo.model.Category;
import com.internet_tehnologije.demo.repository.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final ICategoryRepository repo;

    public CategoryService(ICategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() { return repo.findAll(); }

    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }

    public Category save(Category product) {
        return repo.save(product);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
