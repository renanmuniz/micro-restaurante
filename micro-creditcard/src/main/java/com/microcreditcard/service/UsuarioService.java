package com.microcreditcard.service;

import com.microcreditcard.controller.dto.UsuarioDto;
import com.microcreditcard.controller.form.UsuarioForm;
import com.microcreditcard.modelo.Usuario;
import com.microcreditcard.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioDto> listar(String nomeUsuario, Pageable paginacao) {
        Page<Usuario> usuarios;
        if (nomeUsuario == null) {
            usuarios = usuarioRepository.findAll(paginacao);
        } else {
            usuarios = usuarioRepository.findByNome(nomeUsuario, paginacao);
        }
        return UsuarioDto.converter(usuarios);
    }

    public UsuarioDto cadastrar(UsuarioForm form) {
        Usuario usuario = form.converter();
        usuario.setDtHrCriacao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public UsuarioDto detalhar(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new UsuarioDto(usuarioOptional.get());
        }
        return null;
    }

    public UsuarioDto atualizar(Long id, UsuarioForm form) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository, LocalDateTime.now());
            return new UsuarioDto(usuario);
        }
        return null;
    }

    public Boolean remover(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
