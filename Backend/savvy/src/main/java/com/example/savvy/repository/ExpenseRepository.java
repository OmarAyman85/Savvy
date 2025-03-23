package com.example.savvy.repository;

import com.example.savvy.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository  // Marks this as a Spring repository (optional for JpaRepository)
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // JpaRepository provides built-in CRUD operations, no need to implement them manually.

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllExpenses();

    Optional<Expense> findFirstByOrderByDateDesc();
}
