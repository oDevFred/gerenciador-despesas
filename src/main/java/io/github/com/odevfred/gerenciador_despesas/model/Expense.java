package io.github.com.odevfred.gerenciador_despesas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "A descrição não pode estar vazia")
    @Size(min = 3, max = 100, message = "A descrição deve ter entre 3 a 100 caracteres")
    private String description;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor tem que ser positivo")
    @DecimalMin(value = "0.01", message = "O valor mínimo é de 1 centavo")
    private BigDecimal amount;
    
    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser no futuro")
    private LocalDate date;

    @NotBlank(message = "A categoria é obrigatória")
    @Pattern(regexp = "ALIMENTACAO|TRANSPORTE|LAZER|SAUDE", message = "Categoria inválida")
    private String category;

    // Constructor
    public Expense() {}

    public Expense(Long id, String description, BigDecimal amount, LocalDate date, String category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
