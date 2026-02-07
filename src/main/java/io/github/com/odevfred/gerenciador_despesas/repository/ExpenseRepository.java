package io.github.com.odevfred.gerenciador_despesas.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.com.odevfred.gerenciador_despesas.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :mounth")
    BigDecimal sumAllExpenses(@Param("mounth") int mounth);
}
