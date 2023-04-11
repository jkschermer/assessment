package com.example.assessment.entity;

import jakarta.persistence.*;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int remainingContent;
    private double price;
    private String startDate;

    private String username; // added username property

    public Category getCategory() {
        return category;
    }

    // Constructors, getters, and setters for the fields
}
