package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.ProdutoDto;
import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public Page<ProdutoDto> listar(@RequestParam(required = false)
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        Page<Produto> produtos;
        produtos = produtoRepository.carregaCardapio(paginacao);
        return ProdutoDto.converter(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(produtoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }


}
