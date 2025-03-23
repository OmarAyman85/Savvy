package com.example.savvy.services.stats;

import com.example.savvy.dto.GraphDTO;
import com.example.savvy.dto.StatsDTO;
import com.example.savvy.entity.Expense;
import com.example.savvy.entity.Income;
import com.example.savvy.repository.ExpenseRepository;
import com.example.savvy.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        return graphDTO;
    }

    public StatsDTO getStats() {
        Double totalIncome = incomeRepository.sumAllIncomes();
        Double totalExpense = expenseRepository.sumAllExpenses();

        Optional<Income> income = incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> expense = expenseRepository.findFirstByOrderByDateDesc();


        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        income.ifPresent(statsDTO::setLatestIncome);
        expense.ifPresent(statsDTO::setLatestExpense);

        return statsDTO;
    }
}
