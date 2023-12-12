package br.edu.utfpr.turismoapi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.utfpr.turismoapi.models.Pessoa;
import br.edu.utfpr.turismoapi.repositories.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository PessoaRepository;

    public Page<Pessoa> findAll(Pageable pageable) {
        return this.PessoaRepository.findAll(pageable);
    }

    @Transactional
    public Pessoa save(Pessoa Pessoa) {
        return PessoaRepository.save(Pessoa);
    }

    public boolean existsByIdAndEmail(UUID id, String email) {
        return PessoaRepository.existsByIdAndEmail(id, email);
    }

    public boolean existsByEmail(String email) {
        return PessoaRepository.existsByEmail(email);
    }

    public boolean existsByEmailAndPassword(String email, String password) {
        return PessoaRepository.existsByEmailAndPassword(email, password);
    }

    public Optional<Pessoa> findById(UUID id) {
        return PessoaRepository.findById(id);
    }

    public Optional<Pessoa> findByEmail(String email) {
        return PessoaRepository.findByEmail(email);
    }

    @Transactional
    public void delete(Pessoa Pessoa) {
        this.PessoaRepository.delete(Pessoa);
    }

    public Optional<Pessoa> findByEmailAndPassword(String email, String password) {
        return PessoaRepository.findByEmailAndPassword(email, password);
    }

    public List<Pessoa> findByNome(String nome) {
        return PessoaRepository.findByNome(nome);
    }
}
