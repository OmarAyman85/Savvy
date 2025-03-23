package com.example.savvy.dto;

import com.example.savvy.entity.Expense;
import com.example.savvy.entity.Income;
import lombok.Data;

@Data
public class StatsDTO {

    private Double income;
    private Double expense;
    private Income latestIncome;
    private Expense latestExpense;
}
