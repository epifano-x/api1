package br.edu.utfpr.turismoapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.turismoapi.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    boolean existsByIdAndEmail(UUID id, String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByEmailAndPassword(String email, String password);

    List<Pessoa> findByNome(String nome);   

}
