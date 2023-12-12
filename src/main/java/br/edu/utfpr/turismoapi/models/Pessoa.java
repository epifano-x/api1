package br.edu.utfpr.turismoapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
@Table(name = "tb_pessoa")
public class Pessoa implements UserDetails{
    @Id
    @Column(name = "Id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "Nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "Email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "Password", length = 255, nullable = false) // Armazenar o hash da senha
    private String password;

    @Column(name = "Nascimento")
    private LocalDateTime nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "Perfil", length = 20, nullable = false)
    private Perfil perfil;

    @CreationTimestamp
    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    public enum Perfil {
        CLIENTE, AGENCIA_DE_VIAGENS
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(perfil.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
