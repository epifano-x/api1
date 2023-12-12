package br.edu.utfpr.turismoapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.turismoapi.models.Pacote;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, UUID> {

    @Query("SELECT p FROM Pacote p JOIN FETCH p.tours WHERE p.id = :pacoteId")
    Pacote findByIdWithTours(@Param("pacoteId") UUID pacoteId);
}
