package br.edu.utfpr.turismoapi.dto;

import java.util.List;
import java.util.UUID;

import br.edu.utfpr.turismoapi.models.Pacote.EstadoPacote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PacoteDTO {

    private UUID id;
    private String nome;
    private double preco;
    private String username;
    private List<UUID> tourIds;
    private EstadoPacote estado;
    // Getters and setters
}
