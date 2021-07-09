package com.microrestaurante.controller.form;

public class ProdutoForm {
    public String nome;
    public String descricao;
    public String tamanho;
    public Double preco;

    public ProdutoForm(String nome, String descricao, String tamanho, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
