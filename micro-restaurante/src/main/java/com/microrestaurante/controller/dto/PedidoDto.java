package com.microrestaurante.controller.dto;

import com.microrestaurante.modelo.Pedido;

public class PedidoDto {
    private Long id;
    private String nome;
    private Double valorTotal;
    private Boolean pago;
    private Boolean aceito;
    private Boolean pronto;
    private Boolean entregue;
    private Boolean cancelado;
    private Boolean estornado;
    private String dtHrPedido;
    private String dtHrFinalizado;
    private String uuidpagamento;

    public PedidoDto() {
    }

    public PedidoDto(Long id, String nome, Double valorTotal, Boolean pago, Boolean aceito, Boolean pronto,
                     Boolean entregue, Boolean cancelado, Boolean estornado, String dtHrPedido,
                     String dtHrFinalizado, String uuidpagamento) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.pago = pago;
        this.aceito = aceito;
        this.pronto = pronto;
        this.entregue = entregue;
        this.cancelado = cancelado;
        this.estornado = estornado;
        this.dtHrPedido = dtHrPedido;
        this.dtHrFinalizado = dtHrFinalizado;
        this.uuidpagamento = uuidpagamento;
    }

    public PedidoDto(Pedido pedido) {
        this.id = pedido.id;
        this.valorTotal = pedido.valorTotal;
        this.pago = pedido.pago;
        this.aceito = pedido.aceito;
        this.pronto = pedido.pronto;
        this.entregue = pedido.entregue;
        this.cancelado = pedido.cancelado;
        this.estornado = pedido.estornado;
        this.dtHrPedido = pedido.dtHrPedido.toString();
        this.dtHrFinalizado = pedido.dtHrFinalizado.toString();
        this.uuidpagamento = pedido.uuidpagamento;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Boolean getAceito() {
        return aceito;
    }

    public void setAceito(Boolean aceito) {
        this.aceito = aceito;
    }

    public Boolean getPronto() {
        return pronto;
    }

    public void setPronto(Boolean pronto) {
        this.pronto = pronto;
    }

    public Boolean getEntregue() {
        return entregue;
    }

    public void setEntregue(Boolean entregue) {
        this.entregue = entregue;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Boolean getEstornado() {
        return estornado;
    }

    public void setEstornado(Boolean estornado) {
        this.estornado = estornado;
    }

    public String getDtHrPedido() {
        return dtHrPedido;
    }

    public void setDtHrPedido(String dtHrPedido) {
        this.dtHrPedido = dtHrPedido;
    }

    public String getDtHrFinalizado() {
        return dtHrFinalizado;
    }

    public void setDtHrFinalizado(String dtHrFinalizado) {
        this.dtHrFinalizado = dtHrFinalizado;
    }

    public String getUuidpagamento() {
        return uuidpagamento;
    }

    public void setUuidpagamento(String uuidpagamento) {
        this.uuidpagamento = uuidpagamento;
    }
}
