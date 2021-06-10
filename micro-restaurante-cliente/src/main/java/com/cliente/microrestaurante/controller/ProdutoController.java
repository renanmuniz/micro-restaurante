package com.cliente.microrestaurante.controller;

import com.cliente.microrestaurante.controller.dto.ProdutoDto;
import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.repository.ProdutoRepository;
import com.cliente.microrestaurante.service.ProdutoService;
import com.cliente.microrestaurante.service.UsuarioService;
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
    private ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoDto> listar(@RequestParam(required = false)
                                   @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
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

}
