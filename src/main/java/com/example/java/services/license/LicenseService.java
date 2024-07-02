package com.example.java.services.license;

import com.example.java.dtos.LicenseDTO;
import com.example.java.entities.Category;
import com.example.java.entities.License;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.CategoryRepo;
import com.example.java.repositories.LicenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LicenseService implements ILicenseService{
    private final LicenseRepo licenseRepo;

    @Override
    public License addLicense(LicenseDTO licenseDTO) {
        License license = new License();

        license.setName(licenseDTO.getName());
        license.setNo(licenseDTO.getNo());
        license.setLicenseClass(licenseDTO.getLicenseClass());
        license.setExpires(licenseDTO.getExpires());
        license.setDateOfBirth(licenseDTO.getDateOfBirth());
        return licenseRepo.save(license);
    }

    @Override
    public License updateLicense(Long licenceId, LicenseDTO licenseDTO) throws IdNotFoundException {
        License existingLicense = licenseRepo
                .findById(licenceId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("License id : " + licenceId +" is not found")
                );
        existingLicense.setName(licenseDTO.getName());
        existingLicense.setNo(licenseDTO.getNo());
        existingLicense.setLicenseClass(licenseDTO.getLicenseClass());
        existingLicense.setExpires(licenseDTO.getExpires());
        existingLicense.setDateOfBirth(licenseDTO.getDateOfBirth());
        return licenseRepo.save(existingLicense);
    }

    @Override
    public void deleteLicense(Long licenceId) throws IdNotFoundException {
        License existingLicense = licenseRepo
                .findById(licenceId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("License id : " + licenceId +" is not found")
                );
        licenseRepo.delete(existingLicense);
    }

    @Override
    public License getLicense(Long licenceId) throws IdNotFoundException {
        return licenseRepo
                .findById(licenceId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("License id : " + licenceId +" is not found")
                );
    }

    @Override
    public List<License> getLicenses() {
       return licenseRepo.findAll();
    }
}
