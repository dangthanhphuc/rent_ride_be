package com.example.java.repositories;

import com.example.java.entities.UtilityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityDetailRepo extends JpaRepository<UtilityDetail, Long> {

}
