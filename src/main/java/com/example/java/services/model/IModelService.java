package com.example.java.services.model;

import com.example.java.dtos.CategoryDTO;
import com.example.java.dtos.ModelDTO;
import com.example.java.entities.Category;
import com.example.java.entities.Model;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IModelService {
    Model addModel(ModelDTO modelDTO);
    Model updateModel(Long modelId, ModelDTO modelDTO) throws IdNotFoundException;
    void deleteModel(Long modelId) throws IdNotFoundException;
    Model getModel(Long modelId) throws IdNotFoundException;
    List<Model> getModels();
}
