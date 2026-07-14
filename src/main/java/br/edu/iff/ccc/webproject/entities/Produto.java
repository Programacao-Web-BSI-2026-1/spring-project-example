package br.edu.iff.ccc.webproject.entities;

import java.util.UUID;

/**
 * Produto
 */
public class Produto {

    private UUID id;
    private String nome;
    private String descricao;

    public Produto(UUID id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }   

    public Produto() {
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    

}