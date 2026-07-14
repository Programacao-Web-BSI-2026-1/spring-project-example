package br.edu.iff.ccc.webproject.dto;

public class ProdutoRequest {

    private String nome;
    private String descricao;

    public ProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public ProdutoRequest() {
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

}
