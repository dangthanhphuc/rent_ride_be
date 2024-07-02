package com.example.java.controllers;

import com.example.java.dtos.RoleDTO;
import com.example.java.entities.Role;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.filter.InputInvalidFilter;
import com.example.java.response.ResponseObject;
import com.example.java.response.RoleResponse;
import com.example.java.services.role.IRoleService;
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
@RequestMapping("/roles")
public class RoleController {
    private final IRoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addRole(
            @Valid @RequestBody RoleDTO RoleDTO,
            BindingResult result
    ) throws IdNotFoundException {

        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Role Role = roleService.addRole(RoleDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Role created successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RoleResponse.formRole(Role))
                        .build()
        );
    }

    @PutMapping("/update/{roleId}")
    public ResponseEntity<ResponseObject> updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody RoleDTO RoleDTO,
            BindingResult result
    ) throws IdNotFoundException {
        ResponseEntity<ResponseObject> isInvalid = InputInvalidFilter.checkInvalidInput(result);
        if (isInvalid != null) {
            return isInvalid;
        }

        Role Role = roleService
                .updateRole(roleId, RoleDTO);

        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Role updated successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RoleResponse.formRole(Role))
                        .build()
        );
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<ResponseObject> deleteRole(
            @PathVariable Long roleId) throws IdNotFoundException {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Role deleted successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data("Delete successfully !")
                        .build()
        );
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ResponseObject> getRole(
            @PathVariable Long roleId
    ) throws IdNotFoundException {
        Role Role = roleService.getRole(roleId);
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Role got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(RoleResponse.formRole(Role))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getRoles() {
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Roles got successfully !")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(roles.stream().map(RoleResponse::formRole))
                        .build()
        );
    }
}
