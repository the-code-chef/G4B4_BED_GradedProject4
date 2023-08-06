package com.gl.gp4.service.user;

import com.gl.gp4.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    User createUser(User user);
    
    List<User> getAllUsers();
    
    Optional<User> getUserById(Long id);
    
    void deleteUserById(Long id);
}
