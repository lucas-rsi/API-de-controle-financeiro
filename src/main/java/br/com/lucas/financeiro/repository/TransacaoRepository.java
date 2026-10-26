package br.com.lucas.financeiro.repository;

import br.com.lucas.financeiro.model.Transacao;
import br.com.lucas.financeiro.model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByTipo(TipoTransacao tipo);
    List<Transacao> findByCategoriaIgnoreCase(String categoria);
}
