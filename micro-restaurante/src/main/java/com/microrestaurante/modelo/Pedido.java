package com.microrestaurante.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "id_usuario")
    public Long idUsuario;

    @Column(name = "valortotal")
    public Double valorTotal;

    @Column(name = "pago")
    public Boolean pago;

    @Column(name = "idpgtocartao")
    public String idPgtoCartao;

    @Column(name = "aceito")
    public Boolean aceito;

    @Column(name = "pronto")
    public Boolean pronto;

    @Column(name = "entregue")
    public Boolean entregue;

    @Column(name = "cancelado")
    public Boolean cancelado;

    @Column(name = "estornado")
    public Boolean estornado;

    @Column(name = "uuidpagamento")
    public String uuidpagamento;

    @Column(name = "dthrpedido")
    public LocalDateTime dtHrPedido;

    @Column(name = "dthrfinalizado")
    public LocalDateTime dtHrFinalizado;

    public Pedido() {
    }

    public Pedido(Long idUsuario, LocalDateTime dtHrPedido) {
        this.idUsuario = idUsuario;
        this.dtHrPedido = dtHrPedido;
    }

    public Pedido(Long id, Long idUsuario, Double valorTotal, Boolean pago, String idPgtoCartao, Boolean aceito,
                  Boolean pronto, Boolean entregue, Boolean cancelado, Boolean estornado, String uuidpagamento,
                  LocalDateTime dtHrPedido, LocalDateTime dtHrFinalizado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.valorTotal = valorTotal;
        this.pago = pago;
        this.idPgtoCartao = idPgtoCartao;
        this.aceito = aceito;
        this.pronto = pronto;
        this.entregue = entregue;
        this.cancelado = cancelado;
        this.estornado = estornado;
        this.uuidpagamento = uuidpagamento;
        this.dtHrPedido = dtHrPedido;
        this.dtHrFinalizado = dtHrFinalizado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getIdPgtoCartao() {
        return idPgtoCartao;
    }

    public void setIdPgtoCartao(String idPgtoCartao) {
        this.idPgtoCartao = idPgtoCartao;
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

    public void setDtHrFinalizado(LocalDateTime dthrfinalizado) {
        this.dtHrFinalizado = dthrfinalizado;
    }

    public String getUuidpagamento() {
        return uuidpagamento;
    }

    public void setUuidpagamento(String uuidcompra) {
        this.uuidpagamento = uuidcompra;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", valorTotal=" + valorTotal +
                ", pago=" + pago +
                ", idPgtoCartao='" + idPgtoCartao + '\'' +
                ", aceito=" + aceito +
                ", pronto=" + pronto +
                ", entregue=" + entregue +
                ", cancelado=" + cancelado +
                ", estornado=" + estornado +
                ", uuidcompra='" + uuidpagamento + '\'' +
                ", dtHrPedido=" + dtHrPedido +
                ", dtHrFinalizado=" + dtHrFinalizado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(idUsuario, pedido.idUsuario) && Objects.equals(valorTotal, pedido.valorTotal) && Objects.equals(pago, pedido.pago) && Objects.equals(idPgtoCartao, pedido.idPgtoCartao) && Objects.equals(aceito, pedido.aceito) && Objects.equals(pronto, pedido.pronto) && Objects.equals(entregue, pedido.entregue) && Objects.equals(cancelado, pedido.cancelado) && Objects.equals(estornado, pedido.estornado) && Objects.equals(uuidpagamento, pedido.uuidpagamento) && Objects.equals(dtHrPedido, pedido.dtHrPedido) && Objects.equals(dtHrFinalizado, pedido.dtHrFinalizado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, valorTotal, pago, idPgtoCartao, aceito, pronto, entregue, cancelado, estornado, uuidpagamento, dtHrPedido, dtHrFinalizado);
    }
}
