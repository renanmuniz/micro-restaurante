package com.microrestaurante.controller;

import com.microrestaurante.controller.dto.ProdutoDto;
import com.microrestaurante.controller.form.ProdutoForm;
import com.microrestaurante.service.ProdutoService;
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
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoDto> listar(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        return produtoService.listar(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
        ProdutoDto produtoDto = produtoService.detalhar(id);
        if (produtoDto != null) {
            return ResponseEntity.ok(produtoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody ProdutoForm form,
                                                UriComponentsBuilder uriBuilder) {
        ProdutoDto novoProduto = produtoService.cadastrar(form);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(novoProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
        ProdutoDto produto = produtoService.alterar(id, form);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (produtoService.remover(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
