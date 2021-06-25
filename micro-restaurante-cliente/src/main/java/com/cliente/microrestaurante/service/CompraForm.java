package com.cliente.microrestaurante.service;

public class CompraForm {
    private Long idUsuario;
    private String numeroCartao;
    private Double valor;

    public CompraForm() {
    }

    public CompraForm(Long idUsuario, String numeroCartao, Double valor) {
        this.idUsuario = idUsuario;
        this.numeroCartao = numeroCartao;
        this.valor = valor;
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
