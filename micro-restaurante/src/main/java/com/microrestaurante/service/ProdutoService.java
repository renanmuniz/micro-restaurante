package com.microrestaurante.service;

import com.microrestaurante.controller.dto.ProdutoDto;
import com.microrestaurante.controller.form.ProdutoForm;
import com.microrestaurante.modelo.Produto;
import com.microrestaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoDto> listar(Pageable paginacao) {
        //return ProdutoDto.converter(produtoRepository.carregaCardapio(paginacao));
        return ProdutoDto.converter(produtoRepository.findAll(paginacao));
    }

    public ProdutoDto detalhar(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return new ProdutoDto(produtoOptional.get());
        }
        return null;
    }

    public ProdutoDto cadastrar(ProdutoForm form) {
        Produto produto = new Produto(form.nome, form.descricao, form.tamanho, form.preco, LocalDateTime.now(),null);
        produtoRepository.save(produto);
        return new ProdutoDto(produto);
    }

    public ProdutoDto alterar(Long id, ProdutoForm form) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            if(form.nome!=null && !form.nome.isBlank() && !form.nome.isEmpty()) { produto.setNome(form.nome); }
            if(form.descricao!=null && !form.descricao.isBlank() && !form.descricao.isEmpty()) produto.setDescricao(form.descricao);
            if(form.tamanho!=null && !form.tamanho.isBlank() && !form.tamanho.isEmpty()) produto.setTamanho(form.tamanho);
            if(form.preco!=null && form.preco != 0.0) produto.setPreco(form.preco);
            produto.setDthralteracao(LocalDateTime.now());
            return new ProdutoDto(produtoOptional.get());
        }
        return null;
    }

    public Boolean remover(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
