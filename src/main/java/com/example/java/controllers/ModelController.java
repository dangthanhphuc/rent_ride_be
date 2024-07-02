package com.example.java.controllers;

import com.example.java.dtos.ModelDTO;
import com.example.java.entities.Model;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.ModelResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.model.IModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/models")
public class ModelController {
    private final IModelService modelService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addModel(
            @Valid @RequestBody ModelDTO modelDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Model model = modelService.addModel(modelDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Model created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(ModelResponse.fromModel(model))
                        .build()
        );
    }

    @PutMapping("/update/{modelId}")
    public ResponseEntity<ResponseObject> updateModel(
            @PathVariable Long ModelId,
            @Valid @RequestBody ModelDTO modelDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Model model = modelService
                .updateModel(ModelId, modelDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Model updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(ModelResponse.fromModel(model))
                        .build()
        );
    }

    @DeleteMapping("/delete/{modelId}")
    public ResponseEntity<ResponseObject> deleteModel(
            @PathVariable Long modelId) throws IdNotFoundException {
        modelService.deleteModel(modelId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Model deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ResponseObject> getModel(
            @PathVariable Long ModelId
    ) throws IdNotFoundException {
        Model model = modelService.getModel(ModelId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Model got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(ModelResponse.fromModel(model))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getModels() {
        List<Model> models = modelService.getModels();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Model got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(models.stream().map(ModelResponse::fromModel))
                        .build()
        );
    }
}
