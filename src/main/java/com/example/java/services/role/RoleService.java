package com.example.java.services.role;

import com.example.java.dtos.RoleDTO;
import com.example.java.entities.*;
import com.example.java.exceptions.IdNotFoundException;
import com.example.java.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService {
    private final RoleRepo roleRepo;

    @Override
    public Role addRole(RoleDTO roleDTO) {
        Role role = new Role();

        role.setName(roleDTO.getName());
        return roleRepo.save(role);
    }

    @Override
    public Role updateRole(Long roleId, RoleDTO roleDTO) throws IdNotFoundException {
        Role existingRole = roleRepo
                .findById(roleId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Role id : " + roleDTO +" is not found")
                );

        existingRole.setName(roleDTO.getName());
        return roleRepo.save(existingRole);
    }

    @Override
    public void deleteRole(Long roleId) throws IdNotFoundException {
        Role existingRole = roleRepo
                .findById(roleId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Role id : " + roleId +" is not found")
                );
        roleRepo.delete(existingRole);
    }

    @Override
    public Role getRole(Long roleId) throws IdNotFoundException {
        return roleRepo
                .findById(roleId) // làm lại
                .orElseThrow(
                        () -> new IdNotFoundException("Role id : " + roleId +" is not found")
                );
    }

    @Override
    public List<Role> getRoles() {
        return roleRepo.findAll();
    }
}
