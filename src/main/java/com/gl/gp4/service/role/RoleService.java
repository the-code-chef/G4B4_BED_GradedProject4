package com.gl.gp4.service.role;

import com.gl.gp4.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    
    Role createRole(Role role);
    
    List<Role> getAllRoles();
    
    Optional<Role> getRoleById(Long id);
    
    Role getRoleByName(String roleName);
    
    void deleteRoleById(Long id);
}