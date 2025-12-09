package com.internet_tehnologije.demo.service;
import com.internet_tehnologije.demo.model.Product;
import com.internet_tehnologije.demo.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final IProductRepository repo;

    public ProductService(IProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
