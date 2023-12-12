package br.edu.utfpr.turismoapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.utfpr.turismoapi.models.Passeio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasseioDTO {

    private UUID id;
    private String destino;
    private String itinerario;
    private double preco;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PasseioDTO fromEntity(Passeio passeio) {
        PasseioDTO passeioDTO = new PasseioDTO();
        passeioDTO.setId(passeio.getId());
        passeioDTO.setDestino(passeio.getDestino());
        passeioDTO.setItinerario(passeio.getItinerario());
        passeioDTO.setPreco(passeio.getPreco());
        passeioDTO.setUsername(passeio.getUsername());
        passeioDTO.setCreatedAt(passeio.getCreatedAt());
        passeioDTO.setUpdatedAt(passeio.getUpdatedAt());
        return passeioDTO;
    }
}
