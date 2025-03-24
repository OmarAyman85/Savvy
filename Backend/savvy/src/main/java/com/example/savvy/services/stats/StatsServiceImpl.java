package com.example.savvy.services.stats;

import com.example.savvy.dto.GraphDTO;
import com.example.savvy.dto.StatsDTO;
import com.example.savvy.model.entity.Expense;
import com.example.savvy.model.entity.Income;
import com.example.savvy.repository.ExpenseRepository;
import com.example.savvy.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

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

        statsDTO.setBalance(totalIncome - totalExpense);

        List<Income> incomeList = incomeRepository.findAll();
        List<Expense> expenseList = expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMinIncome(minIncome.orElse(0.0));
        statsDTO.setMaxIncome(maxIncome.orElse(0.0));
        statsDTO.setMinExpense(minExpense.orElse(0.0));
        statsDTO.setMaxExpense(maxExpense.orElse(0.0));



        return statsDTO;
    }
}
