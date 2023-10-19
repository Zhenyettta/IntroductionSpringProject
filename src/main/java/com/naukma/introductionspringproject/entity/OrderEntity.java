package com.naukma.introductionspringproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "order_taken", nullable = false)
    private LocalDateTime orderTaken;

    @Column(name = "order_given")
    private LocalDateTime orderGiven;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "orders_meals",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<MealEntity> meals;
}
