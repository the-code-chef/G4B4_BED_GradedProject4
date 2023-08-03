package com.gl.gp4.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @Column(name = "first_name", nullable = false)
	private String userName;
    
    @Column(name = "password")
	private String password;

    @Column(name = "email")
	private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
