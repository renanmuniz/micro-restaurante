package com.cliente.microrestaurante.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produtos_pedido")
public class ProdutosPedido {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "id_pedido")
    public Long idPedido;

    @NotNull
    @Column(name = "id_produto")
    public Long idProduto;

    @NotNull
    @Positive
    @Column(name = "quantidade")
    public Double quantidade;

    @Column(name = "valor")
    public Double valor;

    @Column(name = "observacao")
    public String observacao;

    public ProdutosPedido() {
    }

    public ProdutosPedido(Long idProduto, Double quantidade, String observacao) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ProdutosPedido{" +
                "id=" + id +
                ", idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", observacao='" + observacao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutosPedido that = (ProdutosPedido) o;
        return Objects.equals(id, that.id) && Objects.equals(idPedido, that.idPedido) && Objects.equals(idProduto, that.idProduto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(valor, that.valor) && Objects.equals(observacao, that.observacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPedido, idProduto, quantidade, valor, observacao);
    }
}
