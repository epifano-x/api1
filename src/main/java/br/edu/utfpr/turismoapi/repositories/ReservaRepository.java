package br.edu.utfpr.turismoapi.repositories;

import br.edu.utfpr.turismoapi.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {

}
