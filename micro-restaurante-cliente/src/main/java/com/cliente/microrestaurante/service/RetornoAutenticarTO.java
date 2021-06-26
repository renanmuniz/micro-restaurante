package com.cliente.microrestaurante.service;

public class RetornoAutenticarTO {
    private String token;
    private String tipo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
