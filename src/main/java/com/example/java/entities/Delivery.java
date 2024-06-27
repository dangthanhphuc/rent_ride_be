package com.example.java.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Khoảng cách giao tối đa
    @Column(name = "max_distance", nullable = false)
    private int maxDistance;

    // Khoảng cách giao miễn phí
    @Column(name = "free_distance", nullable = false)
    private int freeDistance;

    // Phí giao nhận xe mỗi km
    @Column(name = "per_km_fee", nullable = false)
    private int perKmFee;
}
