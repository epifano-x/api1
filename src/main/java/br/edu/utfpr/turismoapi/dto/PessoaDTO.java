package br.edu.utfpr.turismoapi.dto;

import java.time.LocalDateTime;

import br.edu.utfpr.turismoapi.models.Pessoa.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
public class PessoaDTO {
    @NotNull(message = "nome e obrigatorio")
    @Size(min = 2, max = 100, message = "o nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "e um campo obrigatorio")
    @Email(message = "Formato de e-mail invalido")
    private String email;

    @NotNull
    private String password;

    @PastOrPresent
    private LocalDateTime nascimento;

    private Perfil perfil;
}
