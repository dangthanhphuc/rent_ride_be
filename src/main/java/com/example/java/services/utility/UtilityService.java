package com.example.java.services.utility;

import com.example.java.dtos.UtilityDTO;
import com.example.java.entities.Utility;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.UtilityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilityService implements IUtilityService{
    private final UtilityRepo utilityRepo;
    @Override
    public Utility addUtility(UtilityDTO utilityDTO) {
        Utility Utility = new Utility();

        Utility.setName(utilityDTO.getName());
        return utilityRepo.save(Utility);
    }

    @Override
    public Utility updateUtility(Long utilityId, UtilityDTO utilityDTO) throws IdNotFoundException {
        Utility existingUtility = utilityRepo
                .findById(utilityId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Utility id : " + utilityId +" is not found")
                );

        existingUtility.setName(utilityDTO.getName());
        return utilityRepo.save(existingUtility);
    }

    @Override
    public void deleteUtility(Long utilityId) throws IdNotFoundException {
        Utility existingUtility = utilityRepo
                .findById(utilityId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Utility id : " + utilityId +" is not found")
                );
        utilityRepo.delete(existingUtility);
    }

    @Override
    public Utility getUtility(Long utilityId) throws IdNotFoundException {
        return utilityRepo
                .findById(utilityId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Utility id : " + utilityId +" is not found")
                );
    }

    @Override
    public List<Utility> getUtilities() {
        return utilityRepo.findAll();
    }
}
