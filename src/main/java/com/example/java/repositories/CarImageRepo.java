package com.example.java.repositories;

import com.example.java.entities.CarImage;
import com.example.java.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarImageRepo extends JpaRepository<CarImage, Long> {
    @Query(value = "SELECT * FROM car_images WHERE car_id = :id", nativeQuery = true)
    List<CarImage> findCarImagesByCarId(@Param("id") Long carId);
}
