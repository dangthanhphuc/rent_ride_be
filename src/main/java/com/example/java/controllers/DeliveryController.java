package com.example.java.controllers;

import com.example.java.dtos.DeliveryDTO;
import com.example.java.entities.Delivery;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.DeliveryResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.delivery.IDeliveryService;
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
@RequestMapping("/deliveries")
public class DeliveryController {
    private final IDeliveryService deliveryService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addDelivery(
            @Valid @RequestBody DeliveryDTO DeliveryDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Delivery delivery = deliveryService.addDelivery(DeliveryDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Delivery created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(DeliveryResponse.fromDelivery(delivery))
                        .build()
        );
    }

    @PutMapping("/update/{deliveryId}")
    public ResponseEntity<ResponseObject> updateDelivery(
            @PathVariable Long DeliveryId,
            @Valid @RequestBody DeliveryDTO deliveryDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Delivery Delivery = deliveryService
                .updateDelivery(DeliveryId, deliveryDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Delivery updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(DeliveryResponse.fromDelivery(Delivery))
                        .build()
        );
    }

    @DeleteMapping("/delete/{deliveryId}")
    public ResponseEntity<ResponseObject> deleteDelivery(
            @PathVariable Long DeliveryId) throws IdNotFoundException {
        deliveryService.deleteDelivery(DeliveryId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Delivery deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{DeliveryId}")
    public ResponseEntity<ResponseObject> getDelivery(
            @PathVariable Long DeliveryId
    ) throws IdNotFoundException {
        Delivery Delivery = deliveryService.getDelivery(DeliveryId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Delivery got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(DeliveryResponse.fromDelivery(Delivery))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getDeliveries() {
        List<Delivery> deliveries = deliveryService.getDeliveries();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Deliveries got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(deliveries.stream().map(DeliveryResponse::fromDelivery))
                        .build()
        );
    }
}
