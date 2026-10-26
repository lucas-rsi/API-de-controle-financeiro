package br.com.lucas.financeiro.dto;

import java.math.BigDecimal;

public record ResumoFinanceiro(BigDecimal totalReceitas, BigDecimal totalDespesas, BigDecimal saldo) { }
