package com.microcreditcard.controller.dto;

import com.microcreditcard.modelo.Compra;
import org.springframework.data.domain.Page;

public class CompraDto {
    private Long id;
    private Long idcartao;
    private String numerocartao;
    private String titularcartao;
    private Long idusuario;
    private Double valor;
    private String uuidpagamento;

    public CompraDto(Compra compra) {
        this.id = compra.getId();
        this.idcartao = compra.getIdcartao();
        this.numerocartao = compra.getNumerocartao();
        this.titularcartao = compra.getTitularcartao();
        this.idusuario = compra.getIdusuario();
        this.valor = compra.getValor();
        this.uuidpagamento = compra.getUuidpagamento();
    }

    public static Page<CompraDto> converter(Page<Compra> compras) {
        return compras.map(CompraDto::new);
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

    public String getUuidpagamento() {
        return uuidpagamento;
    }

    public void setUuidpagamento(String uuidpagamento) {
        this.uuidpagamento = uuidpagamento;
    }
}
