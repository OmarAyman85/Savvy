package com.example.savvy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO {
    private long id;
    private String title;
    private String description;
    private String category;
    private Integer amount;
    private LocalDate date;
}
