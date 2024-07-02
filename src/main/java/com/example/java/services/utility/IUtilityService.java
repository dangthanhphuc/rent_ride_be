package com.example.java.services.utility;

import com.example.java.dtos.CategoryDTO;
import com.example.java.dtos.UtilityDTO;
import com.example.java.entities.Category;
import com.example.java.entities.Utility;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IUtilityService {
    Utility addUtility(UtilityDTO utilityDTO);
    Utility updateUtility(Long utilityId, UtilityDTO utilityDTO) throws IdNotFoundException;
    void deleteUtility(Long utilityId) throws IdNotFoundException;
    Utility getUtility(Long utilityId) throws IdNotFoundException;
    List<Utility> getUtilities();
}
