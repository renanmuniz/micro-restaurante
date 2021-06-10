package com.cliente.microrestaurante.service;

import com.cliente.microrestaurante.controller.dto.ProdutoDto;
import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoDto> listar(Pageable paginacao) {
        return ProdutoDto.converter(produtoRepository.carregaCardapio(paginacao));
    }

    public ProdutoDto detalhar(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return new ProdutoDto(produtoOptional.get());
        }
        return null;
    }


}
