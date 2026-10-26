package br.com.lucas.financeiro.service;

import br.com.lucas.financeiro.dto.TransacaoRequest;
import br.com.lucas.financeiro.model.TipoTransacao;
import br.com.lucas.financeiro.model.Transacao;
import br.com.lucas.financeiro.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @Mock TransacaoRepository repository;
    @InjectMocks TransacaoService service;

    @Test
    void deveCalcularReceitasDespesasESaldo() {
        when(repository.findAll()).thenReturn(List.of(
                new Transacao("Salário", new BigDecimal("3000.00"), TipoTransacao.RECEITA, "Trabalho", LocalDate.parse("2026-10-05")),
                new Transacao("Aluguel", new BigDecimal("900.00"), TipoTransacao.DESPESA, "Moradia", LocalDate.parse("2026-10-06")),
                new Transacao("Mercado", new BigDecimal("250.00"), TipoTransacao.DESPESA, "Alimentação", LocalDate.parse("2026-10-07"))
        ));
        var resumo = service.resumo();
        assertEquals(new BigDecimal("3000.00"), resumo.totalReceitas());
        assertEquals(new BigDecimal("1150.00"), resumo.totalDespesas());
        assertEquals(new BigDecimal("1850.00"), resumo.saldo());
    }
}
