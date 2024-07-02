package com.example.java.services.role;

import com.example.java.dtos.RoleDTO;
import com.example.java.entities.Role;
import com.example.java.exceptions.IdNotFoundException;

import java.util.List;

public interface IRoleService {
    Role addRole(RoleDTO roleDTO);
    Role updateRole(Long roleId, RoleDTO roleDTO) throws IdNotFoundException;
    void deleteRole(Long roleId) throws IdNotFoundException;
    Role getRole(Long roleId) throws IdNotFoundException;
    List<Role> getRoles();
}
