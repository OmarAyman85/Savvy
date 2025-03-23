package com.example.savvy.dto;

import lombok.Data;
import java.time.LocalDate;

@Data  // Generates getters, setters, toString, equals, and hashCode
public class ExpenseDTO {

    private long id;               // Expense ID
    private String title;          // Title of the expense
    private String description;    // Description of the expense
    private String category;       // Category of expense (e.g., "Food", "Travel")
    private LocalDate date;        // Expense date
    private Integer amount;        // Expense amount
}
