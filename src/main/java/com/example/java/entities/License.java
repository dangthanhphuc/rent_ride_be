package com.example.java.entities;

import com.example.java.enums.LicenseClass;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "licences")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "no", unique = true)
    private Long no;

    @Enumerated(EnumType.STRING)
    @Column(name = "license_class", nullable = false)
    private LicenseClass licenseClass;

    @Column(name = "expires", nullable = false)
    private LocalDate expires;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
}

