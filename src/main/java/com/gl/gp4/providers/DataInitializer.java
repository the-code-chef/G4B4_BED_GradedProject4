package com.gl.gp4.providers;


import com.gl.gp4.entity.Role;
import com.gl.gp4.entity.User;
import com.gl.gp4.repository.RoleRepository;
import com.gl.gp4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        // Create roles
        Role userRole = new Role(null, "USER");
        Role adminRole = new Role(null, "USER");

        roleRepository.saveAll(Arrays.asList(userRole, adminRole));

        // Create users
        User user = new User(null, "user", "password", Arrays.asList(userRole));
        User adminUser = new User(null, "admin", "password", Arrays.asList(adminRole));

        userRepository.saveAll(Arrays.asList(user, adminUser));
    }
}