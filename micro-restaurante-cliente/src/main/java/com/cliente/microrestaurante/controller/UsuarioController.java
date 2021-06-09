package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.UsuarioDto;
import com.cliente.microrestaurante.controller.form.UsuarioForm;
import com.cliente.microrestaurante.modelo.Usuario;
import com.cliente.microrestaurante.repository.UsuarioRepository;
import com.cliente.microrestaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioDto> listar(@RequestParam(required = false) String nomeUsuario,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        return usuarioService.listar(nomeUsuario, paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form,
                                                UriComponentsBuilder uriBuilder) {
        UsuarioDto novoUsuario = usuarioService.cadastrar(form);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(novoUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.detalhar(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
        UsuarioDto usuario = usuarioService.atualizar(id, form);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (usuarioService.remover(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
