package com.example.savvy.dto;

import com.example.savvy.entity.Expense;
import com.example.savvy.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;

    private List<Income> incomeList;
}
