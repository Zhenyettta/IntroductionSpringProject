package com.naukma.introductionspringproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    private static final String NAME_REGEXP = "[A-Z][a-z]+(-[A-Z][a-z]+){0,1}";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(name = "first_name", nullable = false)
    private String firstName;


    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Must be a valid e-mail address")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<OrderEntity> orders;

}


