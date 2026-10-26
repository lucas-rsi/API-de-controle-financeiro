package br.com.lucas.financeiro.controller;

import br.com.lucas.financeiro.dto.ResumoFinanceiro;
import br.com.lucas.financeiro.dto.TransacaoRequest;
import br.com.lucas.financeiro.model.TipoTransacao;
import br.com.lucas.financeiro.model.Transacao;
import br.com.lucas.financeiro.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    private final TransacaoService service;

    public TransacaoController(TransacaoService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao criar(@Valid @RequestBody TransacaoRequest request) { return service.criar(request); }

    @GetMapping
    public List<Transacao> listar(@RequestParam(required = false) TipoTransacao tipo,
                                  @RequestParam(required = false) String categoria) {
        return service.listar(tipo, categoria);
    }

    @GetMapping("/{id}")
    public Transacao buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}")
    public Transacao atualizar(@PathVariable Long id, @Valid @RequestBody TransacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }

    @GetMapping("/resumo")
    public ResumoFinanceiro resumo() { return service.resumo(); }
}
