package com.naukma.introductionspringproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_taken", nullable = false)
    private LocalDateTime orderTaken;

    @Column(name = "order_given")
    private LocalDateTime orderGiven;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "orders_meals",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> meals;
}
