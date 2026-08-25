package br.edu.iff.ccc.webproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoRequest {

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(min = 5, max = 255, message = "A descrição deve conter entre 5 e 255 caracteres.")
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
