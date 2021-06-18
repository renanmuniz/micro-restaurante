package com.microcreditcard.controller.dto;

import com.microcreditcard.modelo.Cartao;
import com.microcreditcard.modelo.Usuario;
import org.springframework.data.domain.Page;

public class CartaoDto {
    private Long id;
    private String numeroCartao;
    private String nome;
    private Double valorCreditoDisponivel;
    private Boolean cartaoBloqueado;

    public CartaoDto(Cartao cartao) {
        this.id = cartao.getId();
        this.numeroCartao = cartao.getNumeroCartao();
        this.nome = cartao.getNome();
        this.valorCreditoDisponivel = cartao.getValorCreditoDisponivel();
        this.cartaoBloqueado = cartao.getCartaoBloqueado();
    }

    public static Page<CartaoDto> converter(Page<Cartao> cartoes) {
        return cartoes.map(CartaoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorCreditoDisponivel() {
        return valorCreditoDisponivel;
    }

    public void setValorCreditoDisponivel(Double valorCreditoDisponivel) {
        this.valorCreditoDisponivel = valorCreditoDisponivel;
    }

    public Boolean getCartaoBloqueado() {
        return cartaoBloqueado;
    }

    public void setCartaoBloqueado(Boolean cartaoBloqueado) {
        this.cartaoBloqueado = cartaoBloqueado;
    }
}
