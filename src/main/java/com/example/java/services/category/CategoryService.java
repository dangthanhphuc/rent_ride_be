package com.example.java.services.category;


import com.example.java.dtos.CategoryDTO;
import com.example.java.entities.Category;
import com.example.java.exceptions.IdNotFoundException;

import com.example.java.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService{

    private final CategoryRepo categoryRepo;

    @Override
    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepo.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDTO categoryDTO) throws IdNotFoundException {
        Category existingCategory = categoryRepo
                .findById(categoryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Category id : " + categoryId +" is not found")
                );

       existingCategory.setName(categoryDTO.getName());
       existingCategory.setDescription(categoryDTO.getDescription());

        return categoryRepo.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) throws IdNotFoundException {
        Category existingCategory = categoryRepo
                .findById(categoryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Category id : " + categoryId +" is not found")
                );
        categoryRepo.delete(existingCategory);
    }

    @Override
    public Category getCategory(Long categoryId) throws IdNotFoundException {
        return categoryRepo
                .findById(categoryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Category id : " + categoryId +" is not found")
                );
    }


    @Override
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }
}
