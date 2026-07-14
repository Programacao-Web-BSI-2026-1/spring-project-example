package br.edu.iff.ccc.webproject.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.iff.ccc.webproject.dto.ProdutoRequest;
import br.edu.iff.ccc.webproject.entities.Produto;
import br.edu.iff.ccc.webproject.repository.ProdutoRepositorio;


@Service
public class ProdutoUserCase {

    private final ProdutoRepositorio produtoRepositorio;
    
    public ProdutoUserCase(ProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public void criarProduto(ProdutoRequest produto ) {
        // Lógica para criar um produto

        // Gerar um ID único para o produto, seguir critérios especificados (ex: UUID, sequência numérica, etc.)
        UUID id = UUID.randomUUID();
        Produto novoProduto = new Produto(id, produto.getNome(), produto.getDescricao());
        this.produtoRepositorio.salvar(novoProduto);


    } 

    public void atualizarProduto() {
        // Lógica para atualizar um produto
    }

    public void deletarProduto() {
        // Lógica para deletar um produto
    }

    public void buscarProduto() {
        // Lógica para buscar um produto
    }

    public void listarProdutos() {
        // Lógica para listar todos os produtos
    }

    public void validarProduto() {
        // Lógica para validar um produto
    }   


}
