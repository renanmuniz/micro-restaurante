package com.cliente.microrestaurante.controller.dto;

public class ProdutosPedidoDto {

    private String nomeProduto;
    private String tamanhoProduto;
    private Double quantidadeProduto;
    private Double precoProduto;
    private Double subtotal;

    public ProdutosPedidoDto() {
    }

    public ProdutosPedidoDto(String nomeProduto, String tamanhoProduto, Double quantidadeProduto, Double precoProduto, Double subtotal) {
        this.nomeProduto = nomeProduto;
        this.tamanhoProduto = tamanhoProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.subtotal = subtotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(String tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public Double getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Double quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
