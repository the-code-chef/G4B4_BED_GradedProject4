package com.gl.gp4.repository;

import com.gl.gp4.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
    
    Role findByName(String name);
}