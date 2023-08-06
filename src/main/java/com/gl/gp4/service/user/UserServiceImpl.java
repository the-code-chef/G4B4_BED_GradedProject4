package com.gl.gp4.service.user;

import com.gl.gp4.entity.Role;
import com.gl.gp4.entity.User;
import com.gl.gp4.repository.UserRepository;
import com.gl.gp4.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User createUser(User user) {
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            List<Role> managedRoles = new ArrayList<>();
            for (Role role : roles) {
                String roleName = role.getName();
                if (roleName != null && !roleName.isEmpty()) {
                    Role existingRole = roleService.getRoleByName(roleName);
                    if (existingRole != null) {
                        managedRoles.add(existingRole); // Add the managed Role instance
                    } else {
                        throw new RuntimeException("Role with name '" + roleName + "' not found.");
                    }
                } else {
                    throw new RuntimeException("Role must have a valid name.");
                }
            }
            user.setRoles(managedRoles); // Set the list of managed roles for the user
        } else {
            throw new RuntimeException("At least one role must be provided.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
