package com.gl.gp4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "first_name", nullable = false)
	private String userName;
    
    @Column(name = "password")
	private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
