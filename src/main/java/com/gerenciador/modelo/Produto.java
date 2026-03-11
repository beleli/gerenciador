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

    @Column
    private String descricao;

    @Column
    private Double preco;

    @Column
    private Integer quantidade;

    @Column
    private String categoria;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, Integer quantidade, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
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

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Produto{id=%d, nome=\"%s\", descricao=%s, preco=%s, quantidade=%d, categoria=%s}",
                id,
                nome,
                descricao == null ? "null" : "\"" + descricao + "\"",
                preco == null ? "null" : String.format("%.2f", preco).replace(',', '.'),
                quantidade,
                categoria == null ? "null" : "\"" + categoria + "\""
        );
    }
}