package com.example.java.repositories;

import com.example.java.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {
    @Query(value = "SELECT * FROM rates WHERE car_id = :id", nativeQuery = true)
    List<Rate> findRatesByCarId(@Param("id") Long carId);
}
