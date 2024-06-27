package com.example.java.repositories;

import com.example.java.entities.Utility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilityRepo extends JpaRepository<Utility, Long> {
}
