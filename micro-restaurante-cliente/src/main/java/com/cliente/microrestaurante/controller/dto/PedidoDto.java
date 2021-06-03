package com.cliente.microrestaurante.controller.dto;

import com.cliente.microrestaurante.modelo.Pedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoDto {
    private Long id;
    private BigDecimal valorTotal;
    private Boolean pago;
    private Boolean aceito;
    private Boolean pronto;
    private Boolean entregue;
    private Boolean cancelado;
    private Boolean estornado;
    private LocalDateTime dtHrPedido;
    private LocalDateTime dtHrFinalizado;

    public PedidoDto(Pedido p) {
        this.id = p.id;
        this.valorTotal = p.valorTotal;
        this.pago = p.pago;
        this.aceito = p.aceito;
        this.pronto = p.pronto;
        this.entregue = p.entregue;
        this.cancelado = p.cancelado;
        this.estornado = p.estornado;
        this.dtHrPedido = p.dtHrPedido;
        this.dtHrFinalizado = p.dtHrFinalizado;
    }

    public static Page<PedidoDto> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
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

    public LocalDateTime getDtHrPedido() {
        return dtHrPedido;
    }

    public void setDtHrPedido(LocalDateTime dtHrPedido) {
        this.dtHrPedido = dtHrPedido;
    }

    public LocalDateTime getDtHrFinalizado() {
        return dtHrFinalizado;
    }

    public void setDtHrFinalizado(LocalDateTime dtHrFinalizado) {
        this.dtHrFinalizado = dtHrFinalizado;
    }
}
