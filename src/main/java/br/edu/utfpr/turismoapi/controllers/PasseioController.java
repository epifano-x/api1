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

import br.edu.utfpr.turismoapi.models.Passeio;
import br.edu.utfpr.turismoapi.repositories.PasseioRepository;

@RestController
@RequestMapping("/passeios")
public class PasseioController {
    @Autowired
    private PasseioRepository passeioRepository;

    @GetMapping
    public List<Passeio> listarPasseios() {
        return passeioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Passeio> buscarPasseioPorId(@PathVariable UUID id) {
        return passeioRepository.findById(id);
    }

    @PostMapping
    public Passeio criarPasseio(@RequestBody Passeio passeio) {
        return passeioRepository.save(passeio);
    }

    @PutMapping("/{id}")
    public Passeio atualizarPasseio(@PathVariable UUID id, @RequestBody Passeio passeioAtualizado) {
        passeioAtualizado.setId(id);
        return passeioRepository.save(passeioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void excluirPasseio(@PathVariable UUID id) {
        passeioRepository.deleteById(id);
    }
}
