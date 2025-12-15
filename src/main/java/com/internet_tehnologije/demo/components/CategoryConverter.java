package com.internet_tehnologije.demo.components;

import com.internet_tehnologije.demo.model.Category;
import com.internet_tehnologije.demo.repository.ICategoryRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<String, Category> {

    private final ICategoryRepository categoryRepository;

    public CategoryConverter(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }
        return categoryRepository.findById(Long.valueOf(source))
                .orElseThrow();
    }
}
