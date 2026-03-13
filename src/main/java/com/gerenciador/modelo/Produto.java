package com.gerenciador.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return String.format("Produto(id=%d, nome=\"%s\", descricao=%s, preco=%s, quantidade=%d)",
                id,
                nome,
                descricao == null ? null : "\"" + descricao + "\"",
                preco == null ? null : String.format("%.2f", preco).replace(',', '.'),
                quantidade
        );
    }
}