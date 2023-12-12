package br.edu.utfpr.turismoapi.dto;

import br.edu.utfpr.turismoapi.models.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private UUID id;
    private UUID pessoaId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID pacoteId;

    public static ReservaDTO fromEntity(Reserva reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setPessoaId(reserva.getPessoa().getId());
        reservaDTO.setCreatedAt(reserva.getCreatedAt());
        reservaDTO.setUpdatedAt(reserva.getUpdatedAt());
        reservaDTO.setPacoteId(reserva.getPacote() != null ? reserva.getPacote().getId() : null);
        return reservaDTO;
    }
}
