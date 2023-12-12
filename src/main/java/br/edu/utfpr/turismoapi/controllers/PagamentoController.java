package br.edu.utfpr.turismoapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.turismoapi.models.Pagamento;
import br.edu.utfpr.turismoapi.repositories.PagamentoRepository;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @GetMapping
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pagamento> buscarPagamentoPorId(@PathVariable UUID id) {
        return pagamentoRepository.findById(id);
    }

    @PostMapping
    public Pagamento criarPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @PutMapping("/{id}")
    public Pagamento atualizarPagamento(@PathVariable UUID id, @RequestBody Pagamento pagamentoAtualizado) {
        pagamentoAtualizado.setId(id); // Certifique-se de definir o ID correto
        return pagamentoRepository.save(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluirPagamento(@PathVariable UUID id) {
        pagamentoRepository.deleteById(id);
    }
}
