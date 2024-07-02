package com.example.java.services.license;

import com.example.java.dtos.CategoryDTO;
import com.example.java.dtos.LicenseDTO;
import com.example.java.entities.Category;
import com.example.java.entities.License;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface ILicenseService {
    License addLicense(LicenseDTO licenceDTO);
    License updateLicense(Long licenseId, LicenseDTO licenseDTO) throws IdNotFoundException;
    void deleteLicense(Long licenseId) throws IdNotFoundException;
    License getLicense(Long licenseId) throws IdNotFoundException;
    List<License> getLicenses();
}
