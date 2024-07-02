package com.example.java.services.category;

import com.example.java.entities.Category;
import com.example.java.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }
}
