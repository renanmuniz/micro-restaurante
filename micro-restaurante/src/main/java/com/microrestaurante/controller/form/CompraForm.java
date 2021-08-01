package com.microrestaurante.controller.form;


public class CompraForm {
    private Long idCompra;
    private Long idUsuario;
    private String numeroCartao;
    private Double valor;

    public CompraForm() {
    }

    public CompraForm(Long idCompra, Long idUsuario, String numeroCartao, Double valor) {
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.numeroCartao = numeroCartao;
        this.valor = valor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }


}
