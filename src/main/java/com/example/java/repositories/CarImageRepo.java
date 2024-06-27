package com.example.java.repositories;

import com.example.java.entities.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepo extends JpaRepository<CarImage, Long> {
}
