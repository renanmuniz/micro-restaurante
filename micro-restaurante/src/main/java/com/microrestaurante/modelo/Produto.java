package com.microrestaurante.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotNull
    @NotEmpty
    @Column(name = "nome")
    public String nome;

    @NotNull
    @NotEmpty
    @Column(name = "descricao")
    public String descricao;

    @NotNull
    @NotEmpty
    @Column(name = "tamanho")
    public String tamanho;

    @NotNull
    @Column(name = "preco")
    public Double preco;

    @NotNull
    @Column(name = "dthrcriacao")
    public LocalDateTime dthrcriacao;

    @Column(name = "dthralteracao")
    public LocalDateTime dthralteracao;

    public Produto() {
    }

    public Produto(String nome, String descricao, String tamanho, Double preco, LocalDateTime dthrcriacao, LocalDateTime dthralteracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
        this.dthrcriacao = dthrcriacao;
        this.dthralteracao = dthralteracao;
    }

    public Produto(Long id, String nome, String descricao, String tamanho, Double preco, LocalDateTime dthrcriacao, LocalDateTime dthralteracao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.preco = preco;
        this.dthrcriacao = dthrcriacao;
        this.dthralteracao = dthralteracao;
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

    public LocalDateTime getDthrcriacao() {
        return dthrcriacao;
    }

    public void setDthrcriacao(LocalDateTime dthrcriacao) {
        this.dthrcriacao = dthrcriacao;
    }

    public LocalDateTime getDthralteracao() {
        return dthralteracao;
    }

    public void setDthralteracao(LocalDateTime dthralteracao) {
        this.dthralteracao = dthralteracao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", preco=" + preco +
                ", dthrcriacao=" + dthrcriacao +
                ", dthralteracao=" + dthralteracao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(tamanho, produto.tamanho) && Objects.equals(preco, produto.preco) && Objects.equals(dthrcriacao, produto.dthrcriacao) && Objects.equals(dthralteracao, produto.dthralteracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, tamanho, preco, dthrcriacao, dthralteracao);
    }
}
