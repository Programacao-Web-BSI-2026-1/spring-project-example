package br.edu.iff.ccc.webproject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto de transferência de dados (DTO) para criação e atualização de produtos")
public class ProdutoRequest {

    @Schema(
        description = "Nome comercial do produto", 
        example = "Teclado Mecânico RGB", 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 e 100 caracteres.")
    private String nome;

    @Schema(
        description = "Descrição detalhada do produto", 
        example = "Teclado mecânico com switches azuis e iluminação personalizável",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
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