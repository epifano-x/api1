package br.edu.utfpr.turismoapi.repositories;

import br.edu.utfpr.turismoapi.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

}
