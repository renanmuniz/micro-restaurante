package com.microcreditcard.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @Column(name = "id_cartao")
    public Long idcartao;

    @Transient
    public String numerocartao;

    @Transient
    public String titularcartao;

    @NotNull
    @Column(name = "id_usuario")
    public Long idusuario;

    @NotNull
    @Positive
    @Column(name = "valor")
    public Double valor;

    @NotNull
    @Column(name = "dthrcompra")
    public LocalDateTime dtHrCompra;

    @Column(name = "aprovada")
    public Boolean aprovada;

    @Column(name = "estornada")
    public Boolean estornada;

    @Column(name = "paga")
    public Boolean paga;

    @Column(name = "uuidpagamento")
    public String uuidpagamento;

    public Compra() {
    }

    public Compra(String numeroCartao, Long idUsuario, Double valor) {
        this.numerocartao = numeroCartao;
        this.idusuario = idUsuario;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdcartao() {
        return idcartao;
    }

    public void setIdcartao(Long idcartao) {
        this.idcartao = idcartao;
    }

    public String getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getTitularcartao() {
        return titularcartao;
    }

    public void setTitularcartao(String titularcartao) {
        this.titularcartao = titularcartao;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDtHrCompra() {
        return dtHrCompra;
    }

    public void setDtHrCompra(LocalDateTime dtHrCompra) {
        this.dtHrCompra = dtHrCompra;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public Boolean getEstornada() {
        return estornada;
    }

    public void setEstornada(Boolean estornada) {
        this.estornada = estornada;
    }

    public Boolean getPaga() {
        return paga;
    }

    public void setPaga(Boolean paga) {
        this.paga = paga;
    }

    public String getUuidpagamento() {
        return uuidpagamento;
    }

    public void setUuidpagamento(String uuidcompra) {
        this.uuidpagamento = uuidcompra;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", idcartao=" + idcartao +
                ", numerocartao='" + numerocartao + '\'' +
                ", titularcartao='" + titularcartao + '\'' +
                ", idusuario=" + idusuario +
                ", valor=" + valor +
                ", dtHrCompra=" + dtHrCompra +
                ", aprovada=" + aprovada +
                ", estornada=" + estornada +
                ", paga=" + paga +
                ", uuidcompra='" + uuidpagamento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id) && Objects.equals(idcartao, compra.idcartao) && Objects.equals(numerocartao, compra.numerocartao) && Objects.equals(titularcartao, compra.titularcartao) && Objects.equals(idusuario, compra.idusuario) && Objects.equals(valor, compra.valor) && Objects.equals(dtHrCompra, compra.dtHrCompra) && Objects.equals(aprovada, compra.aprovada) && Objects.equals(estornada, compra.estornada) && Objects.equals(paga, compra.paga) && Objects.equals(uuidpagamento, compra.uuidpagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idcartao, numerocartao, titularcartao, idusuario, valor, dtHrCompra, aprovada, estornada, paga, uuidpagamento);
    }
}