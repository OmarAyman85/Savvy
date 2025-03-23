package com.example.savvy.services.income;

import com.example.savvy.dto.IncomeDTO;
import com.example.savvy.entity.Income;

import java.util.List;

public interface IncomeService {

    Income createIncome(IncomeDTO incomeDTO);

    List<Income> getAllIncomes();

    Income getIncomeById(Long id);

    Income updateIncome(IncomeDTO incomeDTO, Long id);

    void deleteIncome(Long id);
}
