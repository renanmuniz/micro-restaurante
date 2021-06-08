package com.cliente.microrestaurante.controller.form;

import com.cliente.microrestaurante.modelo.ProdutosPedido;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutosPedidoForm {

    public Long idProduto;

    public Double quantidade;

    public String observacao;

    public ProdutosPedido converter() {
        return new ProdutosPedido(idProduto, quantidade, observacao);
    }

    public ProdutosPedidoForm() {
    }

    public ProdutosPedidoForm(Long idProduto, Double quantidade, String observacao) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ProdutosPedidoForm{" +
                ", idProduto=" + idProduto +
                ", quantidade=" + quantidade +
                ", observacao='" + observacao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutosPedidoForm that = (ProdutosPedidoForm) o;
        return Objects.equals(idProduto, that.idProduto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(observacao, that.observacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto, quantidade, observacao);
    }
}
