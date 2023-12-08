package com.mabaya.task.onlinesponsoredads.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void dropCategories() {
        categoryRepository.dropCategories();
        categoryRepository.drop_category_sequence();
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category save(String name) {
        return categoryRepository.save(new Category(name));
    }
}
