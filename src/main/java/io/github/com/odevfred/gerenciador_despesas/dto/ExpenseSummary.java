package io.github.com.odevfred.gerenciador_despesas.dto;

import java.math.BigDecimal;

public record ExpenseSummary(
    BigDecimal totalAmount,
    long count,
    String message
) {}
