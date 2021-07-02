package com.microrestaurante.modelo;

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

    @Column(name = "cpf")
    public String cpf;


    public Usuario() {
    }

    public Usuario(String nome, String senha, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
    }

    public Usuario(String nome, String senha, String cpf, LocalDateTime dtHrCriacao, LocalDateTime dthralteracao) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.dtHrCriacao = dtHrCriacao;
        this.dthralteracao = dthralteracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
