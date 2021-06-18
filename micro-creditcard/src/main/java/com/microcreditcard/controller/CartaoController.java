package com.microcreditcard.controller;

import com.microcreditcard.controller.dto.CartaoDto;
import com.microcreditcard.controller.dto.UsuarioDto;
import com.microcreditcard.controller.form.CartaoForm;
import com.microcreditcard.controller.form.UsuarioForm;
import com.microcreditcard.service.CartaoService;
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

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    @GetMapping
    public Page<CartaoDto> listar(@RequestParam(required = false) String nome,
                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        return cartaoService.listar(nome, paginacao);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartaoDto> cadastrar(@RequestBody @Valid CartaoForm form,
                                                UriComponentsBuilder uriBuilder) {
        CartaoDto novoCartao = cartaoService.cadastrar(form);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(novoCartao.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCartao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDto> detalhar(@PathVariable Long id) {
        CartaoDto cartao = cartaoService.detalhar(id);
        if (cartao != null) {
            return ResponseEntity.ok(cartao);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CartaoDto> atualizar(@PathVariable Long id, @RequestParam(required = true) Double novoValor) {
        CartaoDto cartao = cartaoService.atualizarLimite(id, novoValor);
        if (cartao != null) {
            return ResponseEntity.ok(cartao);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (cartaoService.remover(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/bloquear/{id}")
    @Transactional
    public ResponseEntity<CartaoDto> bloquear(@PathVariable Long id) {
        CartaoDto cartao = cartaoService.bloquear(id);
        if (cartao != null) {
            return ResponseEntity.ok(cartao);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/desbloquear/{id}")
    @Transactional
    public ResponseEntity<CartaoDto> desbloquear(@PathVariable Long id) {
        CartaoDto cartao = cartaoService.desbloquear(id);
        if (cartao != null) {
            return ResponseEntity.ok(cartao);
        }
        return ResponseEntity.notFound().build();
    }

}
