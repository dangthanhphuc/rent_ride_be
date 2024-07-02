package com.example.java.services.delivery;

import com.example.java.dtos.DeliveryDTO;
import com.example.java.entities.Delivery;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IDeliveryService {
    Delivery addDelivery(DeliveryDTO deliveryDTO);
    Delivery updateDelivery(Long deliveryId, DeliveryDTO deliveryDTO) throws IdNotFoundException;
    void deleteDelivery(Long deliveryId) throws IdNotFoundException;
    Delivery getDelivery(Long deliveryId) throws IdNotFoundException;
    List<Delivery> getDeliveries();
}
