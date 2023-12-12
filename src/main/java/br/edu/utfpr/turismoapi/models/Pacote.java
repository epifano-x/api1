package br.edu.utfpr.turismoapi.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "tb_pacote")
public class Pacote extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private double preco;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPacote estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pacote_passeio",
            joinColumns = @JoinColumn(name = "pacote_id"),
            inverseJoinColumns = @JoinColumn(name = "passeio_id"))
    private List<Passeio> tours;


    public enum EstadoPacote {
        DISPONIVEL,
        INDISPONIVEL
    }

    // Getters and setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Passeio> getTours() {
        return tours;
    }

    public void setTours(List<Passeio> tours) {
        this.tours = tours;
    }

}
