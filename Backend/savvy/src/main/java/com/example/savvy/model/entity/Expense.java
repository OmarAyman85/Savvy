package com.example.savvy.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity  // Marks this class as a JPA entity (maps to a database table)
@Data    // Generates getter, setter, toString, equals, and hashCode methods
@Table(name = "expense")
public class Expense {

    @Id  // Marks this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Uses database auto-increment to generate unique IDs
    private long id;

    private String title;         // Expense title (e.g., "Lunch")
    private String description;   // Detailed description of the expense
    private String category;      // Category of expense (e.g., "Food", "Travel")
    private LocalDate date;       // Date when the expense occurred
    private Integer amount;       // Amount spent in the expense
}
