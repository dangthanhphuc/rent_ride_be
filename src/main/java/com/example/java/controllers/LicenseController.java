package com.example.java.controllers;

import com.example.java.dtos.LicenseDTO;
import com.example.java.entities.License;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.LicenseResponse;
import com.example.java.response.ResponseObject;
import com.example.java.services.license.ILicenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/licences")
public class LicenseController {
    private final ILicenseService licenseService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addLicense(
            @Valid @RequestBody LicenseDTO licenseDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        License license = licenseService.addLicense(licenseDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("License created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(LicenseResponse.fromLicense(license))
                        .build()
        );
    }

    @PutMapping("/update/{licenseId}")
    public ResponseEntity<ResponseObject> updateLicense(
            @PathVariable Long LicenseId,
            @Valid @RequestBody LicenseDTO LicenseDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        License license = licenseService
                .updateLicense(LicenseId, LicenseDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("License updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(LicenseResponse.fromLicense(license))
                        .build()
        );
    }

    @DeleteMapping("/delete/{licenseId}")
    public ResponseEntity<ResponseObject> deleteLicense(
            @PathVariable Long licenseId) throws IdNotFoundException {
        licenseService.deleteLicense(licenseId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("License deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<ResponseObject> getLicense(
            @PathVariable Long LicenseId
    ) throws IdNotFoundException {
        License license = licenseService.getLicense(LicenseId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("License got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(LicenseResponse.fromLicense(license))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getLicenses() {
        List<License> licenses = licenseService.getLicenses();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("License got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(licenses.stream().map(LicenseResponse::fromLicense))
                        .build()
        );
    }
}
