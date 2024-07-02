package com.example.java.services.utility;

import com.example.java.entities.Utility;
import com.example.java.repositories.UtilityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilityService implements IUtilityService{
    private final UtilityRepo utilityRepo;
    @Override
    public List<Utility> getUtilities() {
        return utilityRepo.findAll();
    }
}
