package com.cliente.microrestaurante.controller.dto;

import com.cliente.microrestaurante.modelo.Produto;
import com.cliente.microrestaurante.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private String tamanho;
    private BigDecimal preco;

    public ProdutoDto(Produto p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.descricao = p.getDescricao();
        this.tamanho = p.getTamanho();
        this.preco = p.preco;
    }

    public static Page<ProdutoDto> converter(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
