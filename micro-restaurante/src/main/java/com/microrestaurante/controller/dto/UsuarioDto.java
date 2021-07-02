package com.microrestaurante.controller.dto;

import com.microrestaurante.modelo.Usuario;
import org.springframework.data.domain.Page;

public class UsuarioDto {
    private Long id;
    private String nome;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }

    public static Page<UsuarioDto> converter(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioDto::new);
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
}
