package io.github.com.odevfred.gerenciador_despesas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.com.odevfred.gerenciador_despesas.dto.ExpenseSummary;
import io.github.com.odevfred.gerenciador_despesas.model.Expense;
import io.github.com.odevfred.gerenciador_despesas.repository.ExpenseRepository;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseRepository repository;

    public ExpenseController(ExpenseRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Expense> listAllExpenses() {
        return repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Expense> listExpenseById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/summary")
    public ResponseEntity<ExpenseSummary> getSummary() {
        List<Expense> allExpenses = repository.findAll();

        BigDecimal total = allExpenses.stream()
                                    .map(e -> e.getAmount() != null ? e.getAmount() : BigDecimal.ZERO)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        ExpenseSummary summary = new ExpenseSummary(
            total,
            allExpenses.size(),
            "Resumo financeiro gerado com sucesso"
        );

        return ResponseEntity.ok(summary);
    }
    

    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        return ResponseEntity.ok(repository.save(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) {
        
        return repository.findById(id)
                        .map(existingExpense -> {
                            expense.setId(id);
                            Expense updated= repository.save(expense);
                            return ResponseEntity.ok(updated);
                        })
                        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    
}
