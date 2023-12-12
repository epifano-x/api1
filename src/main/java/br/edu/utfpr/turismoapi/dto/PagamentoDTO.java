package br.edu.utfpr.turismoapi.dto;

import br.edu.utfpr.turismoapi.models.Pagamento;
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
public class PagamentoDTO {

    private UUID id;
    private UUID reservaId;
    private double valor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PagamentoDTO fromEntity(Pagamento pagamento) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setReservaId(pagamento.getReserva().getId());
        pagamentoDTO.setValor(pagamento.getValor());
        pagamentoDTO.setCreatedAt(pagamento.getCreatedAt());
        pagamentoDTO.setUpdatedAt(pagamento.getUpdatedAt());
        return pagamentoDTO;
    }
}
