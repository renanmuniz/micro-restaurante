package com.microcreditcard.modelo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="usuario")
public class Usuario implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @NotEmpty
    @Column(name = "nome")
    public String nome;

    @NotNull
    @NotEmpty
    @Column(name = "senha")
    public String senha;

    @NotNull
    @Column(name = "dthrcriacao")
    public LocalDateTime dtHrCriacao;

    @Column(name = "dthralteracao")
    public LocalDateTime dthralteracao;


    public Usuario() {
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String nome, String senha, LocalDateTime dtHrCriacao, LocalDateTime dthralteracao) {
        this.nome = nome;
        this.senha = senha;
        this.dtHrCriacao = dtHrCriacao;
        this.dthralteracao = dthralteracao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDtHrCriacao() {
        return dtHrCriacao;
    }

    public void setDtHrCriacao(LocalDateTime dtHrCriacao) {
        this.dtHrCriacao = dtHrCriacao;
    }

    public LocalDateTime getDthralteracao() {
        return dthralteracao;
    }

    public void setDthralteracao(LocalDateTime dthralteracao) {
        this.dthralteracao = dthralteracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id)
                && Objects.equals(nome, usuario.nome)
                && Objects.equals(senha, usuario.senha)
                && Objects.equals(dtHrCriacao, usuario.dtHrCriacao)
                && Objects.equals(dthralteracao, usuario.dthralteracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, senha, dtHrCriacao, dthralteracao);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", dtHrCriacao=" + dtHrCriacao +
                ", dthralteracao=" + dthralteracao +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
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
