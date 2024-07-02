package com.example.java.services.model;

import com.example.java.dtos.ModelDTO;
import com.example.java.entities.Brand;
import com.example.java.entities.Category;
import com.example.java.entities.Model;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.BrandRepo;
import com.example.java.repositories.CategoryRepo;
import com.example.java.repositories.ModelRepo;
import com.example.java.response.BrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelService implements IModelService{
    private final ModelRepo modelRepo;
    private final BrandRepo brandRepo;
    private final CategoryRepo categoryRepo;
    @Override
    public Model addModel(ModelDTO modelDTO) {
        Model model = new Model();

        model.setName(modelDTO.getName());
        model.setFuel(modelDTO.getFuel());
        model.setGearbox(modelDTO.getGearbox());
        model.setSeat(modelDTO.getSeat());
        model.setProductionYear(modelDTO.getProductionYear());
        Brand brand = brandRepo.findById(modelDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Category category = categoryRepo.findById(modelDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        model.setBrand(brand);
        model.setCategory(category);
        return modelRepo.save(model);
    }

    @Override
    public Model updateModel(Long modelId, ModelDTO modelDTO) throws IdNotFoundException {
        Model existingModel = modelRepo.findById(modelId)
                .orElseThrow(() -> new IdNotFoundException("Model id: " + modelId + " is not found"));

        existingModel.setName(modelDTO.getName());
        existingModel.setFuel(modelDTO.getFuel());
        existingModel.setGearbox(modelDTO.getGearbox());
        existingModel.setSeat(modelDTO.getSeat());
        existingModel.setProductionYear(modelDTO.getProductionYear());

        Brand brand = brandRepo.findById(modelDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Category category = categoryRepo.findById(modelDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existingModel.setBrand(brand);
        existingModel.setCategory(category);
        return modelRepo.save(existingModel);
    }

    @Override
    public void deleteModel(Long modelId) throws IdNotFoundException {
        Model existingModel = modelRepo
                .findById(modelId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Model id : " + modelId +" is not found")
                );
        modelRepo.delete(existingModel);
    }

    @Override
    public Model getModel(Long modelId) throws IdNotFoundException {
        return modelRepo
                .findById(modelId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Model id : " + modelId +" is not found")
                );
    }

    @Override
    public List<Model> getModels() {
        return modelRepo.findAll();
    }
}
