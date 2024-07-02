package com.example.java.services.delivery;

import com.example.java.dtos.DeliveryDTO;
import com.example.java.entities.Category;
import com.example.java.entities.Delivery;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CategoryRepo;
import com.example.java.repositories.DeliveryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService implements IDeliveryService{
    private final DeliveryRepo deliveryRepo;

    @Override
    public Delivery addDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setFreeDistance(deliveryDTO.getFreeDistance());
        delivery.setMaxDistance(deliveryDTO.getMaxDistance());
        delivery.setPerKmFee(deliveryDTO.getPerKmFee());
        return deliveryRepo.save(delivery);
    }

    @Override
    public Delivery updateDelivery(Long deliveryId, DeliveryDTO deliveryDTO) throws IdNotFoundException {
        Delivery existingDelivery = deliveryRepo
                .findById(deliveryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Delivery id : " + deliveryId +" is not found")
                );

        existingDelivery.setPerKmFee(deliveryDTO.getPerKmFee());
        existingDelivery.setFreeDistance(deliveryDTO.getFreeDistance());
        existingDelivery.setMaxDistance(deliveryDTO.getMaxDistance());
        return deliveryRepo.save(existingDelivery);
    }

    @Override
    public void deleteDelivery(Long deliveryId) throws IdNotFoundException {
        Delivery existingDelivery = deliveryRepo
                .findById(deliveryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Delivery id : " + deliveryId +" is not found")
                );
        deliveryRepo.delete(existingDelivery);
    }

    @Override
    public Delivery getDelivery(Long deliveryId) throws IdNotFoundException {
        return deliveryRepo
                .findById(deliveryId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Delivery id : " + deliveryId +" is not found")
                );
    }

    @Override
    public List<Delivery> getDeliveries() {
        return deliveryRepo.findAll();
    }
}
