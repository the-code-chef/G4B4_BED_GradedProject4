package com.gl.gp4.entity;

import jakarta.persistence.*;

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
    
	private String password;

    @Column(name = "email")
	private String email;
}