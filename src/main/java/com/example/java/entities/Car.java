package com.example.java.entities;

import com.example.java.enums.Fuel;
import com.example.java.enums.Gearbox;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "mortgage", nullable = false)
    private boolean mortgage;

    @Column(name = "rent", nullable = false)
    private boolean rent;

    @Column(name = "price", nullable = false)
    private int price;

    // Dùng để lọc xe có tài xế
    @Column(name = "driver" , nullable = false)
    private boolean driver;

    @Column(name = "max_km", nullable = false)
    private int maxKm;

    @Column(name = "over_fee", nullable = false)
    private int overFee;

    @Column(name = "instant", columnDefinition = "bit default 0")
    private boolean instant;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    // Cho phép null, vì xe có thể không giao tận nơi
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // Xác định chủ sở hữu
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "car")
    private List<UtilityDetail> utilityDetail;

    @OneToMany(mappedBy = "car")
    private List<Rate> rates;

}

