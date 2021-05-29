package com.cliente.microrestaurante.controller.form;

import com.cliente.microrestaurante.modelo.Usuario;
import com.cliente.microrestaurante.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UsuarioForm {
    private String usuario;
    private String senha;

    public UsuarioForm() {
    }

    public UsuarioForm(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
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

    public Usuario converter() {
        String senhaCript = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.usuario,senhaCript);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository, LocalDateTime now) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setNome(this.usuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
        usuario.setDthralteracao(now);
        return usuario;
    }
}
