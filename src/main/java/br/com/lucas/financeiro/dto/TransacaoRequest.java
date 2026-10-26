package br.com.lucas.financeiro.dto;

import br.com.lucas.financeiro.model.TipoTransacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequest(
        @NotBlank @Size(max = 100) String descricao,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
        @NotNull TipoTransacao tipo,
        @NotBlank @Size(max = 60) String categoria,
        @NotNull LocalDate data
) { }
