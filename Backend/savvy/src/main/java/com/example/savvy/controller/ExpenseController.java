package com.example.savvy.controller;

import com.example.savvy.dto.ExpenseDTO;
import com.example.savvy.model.entity.Expense;
import com.example.savvy.services.expense.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Marks this class as a Spring MVC REST Controller (handles HTTP requests)
@RequestMapping("/api/expense")  // Defines the base URL path for all endpoints in this controller
@RequiredArgsConstructor  // Lombok annotation to generate a constructor with final fields (Dependency Injection)
@CrossOrigin("*")  // Enables Cross-Origin Resource Sharing (CORS) for all origins
public class ExpenseController {

    private final ExpenseService expenseService;  // Injecting ExpenseService to handle business logic

    /**
     * Handles HTTP POST requests to create a new expense.
     *
     * @param dto The ExpenseDTO object received in the request body.
     * @return ResponseEntity with the created Expense or a BAD_REQUEST status if the creation fails.
     */
    @PostMapping  // Maps this method to an HTTP POST request (URL: /api/expense)
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO expenseDTO) {
        Expense createdExpense = expenseService.postExpense(expenseDTO);  // Call service layer to create an expense

        if (createdExpense != null) {
            // If expense was successfully created, return HTTP 201 (Created) with the new expense data
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        } else {
            // If expense creation failed, return HTTP 400 (Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG ...");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        try {
            return ResponseEntity.ok(expenseService.updateExpense(expenseDTO, id));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG ...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SOMETHING WENT WRONG ...");
        }
    }
}
