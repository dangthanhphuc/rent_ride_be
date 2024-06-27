package com.example.java.entities;

import com.example.java.enums.Fuel;
import com.example.java.enums.Gearbox;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "seat", nullable = false)
    private int seat;

    @Column(name = "production_year", nullable = false)
    private LocalDate productionYear;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel", nullable = false)
    private Fuel fuel;

    @Enumerated(EnumType.STRING)
    @Column(name = "gearbox", nullable = false)
    private Gearbox gearbox;
}
