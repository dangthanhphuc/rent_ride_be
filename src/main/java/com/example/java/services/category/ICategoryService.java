package com.example.java.services.category;

import com.example.java.dtos.CategoryDTO;
import com.example.java.entities.Category;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface ICategoryService {
    Category addCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long categoryId, CategoryDTO categoryDTO) throws IdNotFoundException;
    void deleteCategory(Long categoryId) throws IdNotFoundException;
    Category getCategory(Long categoryId) throws IdNotFoundException;
    List<Category> getCategories();
}
