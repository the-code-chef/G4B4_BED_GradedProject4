package com.gl.gp4.service.user;

import com.gl.gp4.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User createUser(User user);
    
    List<User> getAllUsers();
    
    User getUserById(Long id);
    
    void deleteUserById(Long id);
    
    User findUserByUsername(String username);
}