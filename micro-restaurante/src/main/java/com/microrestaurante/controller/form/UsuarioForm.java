package com.microrestaurante.controller.form;


import com.microrestaurante.modelo.Usuario;
import com.microrestaurante.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UsuarioForm {
    private String usuario;
    private String senha;
    private String cpf;

    public UsuarioForm() {
    }

    public UsuarioForm(String usuario, String senha, String cpf) {
        this.usuario = usuario;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario converter() {
        String senhaCript = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.usuario,senhaCript, this.cpf);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository, LocalDateTime now) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setNome(this.usuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        usuario.setCpf(this.cpf);
        usuario.setDthralteracao(now);
        return usuario;
    }
}
