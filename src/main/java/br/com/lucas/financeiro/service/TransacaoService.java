package br.com.lucas.financeiro.service;

import br.com.lucas.financeiro.dto.ResumoFinanceiro;
import br.com.lucas.financeiro.dto.TransacaoRequest;
import br.com.lucas.financeiro.model.TipoTransacao;
import br.com.lucas.financeiro.model.Transacao;
import br.com.lucas.financeiro.repository.TransacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransacaoService {
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) { this.repository = repository; }

    public Transacao criar(TransacaoRequest request) {
        return repository.save(new Transacao(request.descricao(), request.valor(), request.tipo(), request.categoria(), request.data()));
    }

    public List<Transacao> listar(TipoTransacao tipo, String categoria) {
        if (tipo != null) return repository.findByTipo(tipo);
        if (categoria != null && !categoria.isBlank()) return repository.findByCategoriaIgnoreCase(categoria);
        return repository.findAll();
    }

    public Transacao buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada"));
    }

    public Transacao atualizar(Long id, TransacaoRequest request) {
        Transacao transacao = buscar(id);
        transacao.atualizar(request.descricao(), request.valor(), request.tipo(), request.categoria(), request.data());
        return repository.save(transacao);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada");
        repository.deleteById(id);
    }

    public ResumoFinanceiro resumo() {
        List<Transacao> transacoes = repository.findAll();
        BigDecimal receitas = transacoes.stream().filter(t -> t.getTipo() == TipoTransacao.RECEITA)
                .map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal despesas = transacoes.stream().filter(t -> t.getTipo() == TipoTransacao.DESPESA)
                .map(Transacao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ResumoFinanceiro(receitas, despesas, receitas.subtract(despesas));
    }
}
