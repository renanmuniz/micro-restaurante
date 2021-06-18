package com.microcreditcard.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Table(name = "cartao")
public class Cartao {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @NotEmpty
    @Column(name = "numero_cartao")
    public String numeroCartao;

    @NotNull
    @NotEmpty
    @Column(name = "nome")
    public String nome;

    @Positive
    @Column(name = "valor_credito_disp")
    public Double valorCreditoDisponivel;

    @Column(name = "cartao_bloqueado")
    public Boolean cartaoBloqueado;

    public Cartao() {
    }

    public Cartao(String nome, Double valorCreditoDisponivel) {
        this.nome = nome;
        this.valorCreditoDisponivel = valorCreditoDisponivel;
    }

    public Cartao(String numeroCartao, String nome, Double valorCreditoDisponivel, Boolean cartaoBloqueado) {
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.valorCreditoDisponivel = valorCreditoDisponivel;
        this.cartaoBloqueado = cartaoBloqueado;
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

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", nome='" + nome + '\'' +
                ", valorCreditoDisponivel=" + valorCreditoDisponivel +
                ", cartaoBloqueado=" + cartaoBloqueado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(id, cartao.id) && Objects.equals(numeroCartao, cartao.numeroCartao) && Objects.equals(nome, cartao.nome) && Objects.equals(valorCreditoDisponivel, cartao.valorCreditoDisponivel) && Objects.equals(cartaoBloqueado, cartao.cartaoBloqueado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCartao, nome, valorCreditoDisponivel, cartaoBloqueado);
    }
}
