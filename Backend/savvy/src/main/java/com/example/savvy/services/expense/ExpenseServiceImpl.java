package com.example.savvy.services.expense;

import com.example.savvy.dto.ExpenseDTO;
import com.example.savvy.model.entity.Expense;
import com.example.savvy.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ExpenseServiceImpl is the implementation of ExpenseService.
 * This service handles all business logic related to managing expenses.
 * It interacts with the ExpenseRepository to perform database operations.
 */
@Service  // Marks this class as a service component for dependency injection
@RequiredArgsConstructor  // Generates a constructor with required final fields (expenseRepository)
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;  // Injects ExpenseRepository for database operations

    /**
     * Creates a new expense and saves it to the database.
     * @param expenseDTO DTO containing the expense details.
     * @return The saved Expense entity.
     */
    @Override
    public Expense postExpense(ExpenseDTO expenseDTO) {
        Expense expense = new Expense();  // Create a new Expense entity
        return saveOrUpdateExpense(expense, expenseDTO);  // Save data to DB
    }

    /**
     * Saves or updates an Expense entity using data from ExpenseDTO.
     * @param expense The expense entity (new or existing).
     * @param expenseDTO The DTO containing updated data.
     * @return The saved Expense entity.
     */
    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        // Map fields from DTO to entity
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        // Save entity to database using JPA repository
        return expenseRepository.save(expense);
    }

    /**
     * Retrieves all expenses from the database, sorted by date (newest first).
     * @return A list of all expenses sorted in descending order.
     */
    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed()) // Sort by date (latest first)
                .collect(Collectors.toList());
    }

    /**
     * Fetches an expense by its ID.
     * @param id The ID of the expense to retrieve.
     * @return The Expense entity if found.
     * @throws EntityNotFoundException if the expense is not found.
     */
    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with id " + id + " not found."));
    }

    /**
     * Updates an existing expense with new data.
     * @param expenseDTO The DTO containing the updated expense details.
     * @param id The ID of the expense to update.
     * @return The updated Expense entity.
     * @throws EntityNotFoundException if the expense is not found.
     */
    @Override
    public Expense updateExpense(ExpenseDTO expenseDTO, Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense with id " + id + " not found."));
        return saveOrUpdateExpense(expense, expenseDTO);
    }

    /**
     * Deletes an expense by its ID.
     * @param id The ID of the expense to delete.
     * @throws EntityNotFoundException if the expense does not exist.
     */
    @Override
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {  // Check if expense exists
            throw new EntityNotFoundException("Expense with id " + id + " not found.");
        }
        expenseRepository.deleteById(id);  // Delete expense
    }
}
