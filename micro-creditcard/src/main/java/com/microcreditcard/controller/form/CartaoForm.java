package com.microcreditcard.controller.form;

import com.microcreditcard.modelo.Cartao;
import com.microcreditcard.modelo.Usuario;
import com.microcreditcard.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class CartaoForm {
    private String nome;
    private Double valorCredito;

    public CartaoForm() {
    }

    public CartaoForm(String nome, Double valor) {
        this.nome = nome;
        this.valorCredito = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(Double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Cartao converter() {
        return new Cartao(this.nome,valorCredito);
    }

}
